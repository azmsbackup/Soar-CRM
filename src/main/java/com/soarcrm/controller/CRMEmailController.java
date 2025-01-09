package com.soarcrm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.soarcrm.domain.CRMEmail;
import com.soarcrm.services.CRMUserService;
import com.soarcrm.util.AllzoneCRMUtil;

@Controller
public class CRMEmailController {
	@Autowired
	CRMUserService CRMuserService;

	@RequestMapping("/viewemail")
	public ModelAndView viewemail(HttpServletRequest request, HttpServletResponse response, CRMEmail mail)
			throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			String host = "poppro.zoho.com";
			String mailStoreType = "pop3";
			final String username = "kavibharathi.natarajan@znifa.com";
			final String password = "God's daughter";

			List<CRMEmail> maillist = receiveEmail(host, mailStoreType, username, password, mail, request);

			return new ModelAndView("crm-viewemail", "maillist", maillist);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/unreademail")
	public ModelAndView unreademail(HttpServletRequest request, HttpServletResponse response, CRMEmail mail)
			throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			Properties properties = new Properties();
			properties.setProperty("mail.pop3.host", "poppro.zoho.com");
			properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.setProperty("mail.pop3.socketFactory.fallback", "false");
			properties.setProperty("mail.pop3.port", "995");
			properties.setProperty("mail.pop3.socketFactory.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			properties.put("mail.pop3.auth", "true");
			properties.put("mail.debug", "true");
			properties.put("mail.store.protocol", "pop3");
			properties.put("mail.transport.protocol", "pop3");
			properties.put("mail.debug.auth", "true");
			properties.setProperty("mail.pop3.socketFactory.fallback", "false");

			Session emailSession = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("kavibharathi.natarajan@znifa.com", "God's daughter");
				}
			});
			Store emailStore = emailSession.getStore("pop3");
			emailStore.connect("poppro.zoho.com", "kavibharathi.natarajan@znifa.com", "God's daughter");
			Folder inbox = emailStore.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			// Fetch unseen messages from inbox folder
			Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

			// Sort messages from recent to oldest
			Arrays.sort(messages, (m1, m2) -> {
				try {
					return m2.getSentDate().compareTo(m1.getSentDate());
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
			});

			System.out.println("messages.length " + messages.length);

			for (Message message : messages) {
				System.out.println("sendDate: " + message.getSentDate() + " subject:" + message.getSubject());
			}

			return new ModelAndView("crm-unreademail", "crm-unreademail", null);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	private List<CRMEmail> receiveEmail(String pop3Host, String storeType, final String user, final String password,
			CRMEmail mail, HttpServletRequest request) throws IOException {
		List<CRMEmail> maillist = new ArrayList<CRMEmail>();
		try {
			Properties properties = new Properties();
			properties.put("mail.pop3.host", pop3Host);
			properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.pop3.socketFactory.fallback", "false");
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.socketFactory.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			properties.put("mail.pop3.auth", "true");
			properties.put("mail.debug", "true");
			properties.put("mail.store.protocol", "pop3");
			properties.put("mail.transport.protocol", "pop3");
			properties.put("mail.debug.auth", "true");
			properties.put("mail.pop3.socketFactory.fallback", "false");

			Session emailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});

			Store emailStore = emailSession.getStore("pop3");
			emailStore.connect(pop3Host, user, password);

			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_WRITE);
			Message[] messages = emailFolder.getMessages();

			FetchProfile fp = new FetchProfile();
			// fp.add(UIDFolder.FetchProfileItem.UID);
			emailFolder.fetch(messages, fp);

			for (int j = messages.length - 1; j >= 0; j--) {
				CRMEmail email = new CRMEmail();
				Message message = messages[j];
				Address messagefrom = message.getFrom()[0];

				String receipient = String.valueOf(messagefrom);
				receipient = receipient.replaceAll("\\<.*?\\>", "");
				email.setRecipient(receipient);

				int first = String.valueOf(messagefrom).indexOf("<");
				int last = String.valueOf(messagefrom).indexOf(">");

				String result = "";
				if (first != -1 && last != -1) {
					result = String.valueOf(messagefrom).substring(first + 1, last);
				}

				if (!user.equals(result)) {
					email.setSubject(message.getSubject());
					String str = String.valueOf(message.getFrom()[0]);
					str = String.valueOf(message.getFrom()[0]).replaceAll("\"", "");
					email.setFrom(str);
					// email.setContent(message.getContent().toString());

					String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
					String year[] = currentDate.split("-");
					String splitdate[] = year[2].split(":");
					String secondspliteddate[] = splitdate[0].split(" ");
					String monthString = new DateFormatSymbols().getMonths()[Integer.valueOf(year[1]) - 1];
					String month = monthString.substring(0, 3);
					String date = String.valueOf(message.getSentDate());
					String[] words = date.split(" ");

					if (Integer.valueOf(year[0]).equals(Integer.valueOf(words[5]))) {
						if (month != words[1] && year[2] != words[2]) {
							email.setTime(words[1] + " " + words[2]);
						}
						if (secondspliteddate[0].equals(words[2]) && month.equals(words[1])) {
							String[] time = words[3].split(":");

							if ((Integer.valueOf(time[0]) >= 12) && (Integer.valueOf(time[1]) >= 00)) {
								int totaltime = Integer.valueOf(time[0]) - 12;
								if (totaltime == 0) {
									totaltime = 12;
								}
								email.setTime(totaltime + ":" + time[1] + " PM");
							} else {
								email.setTime(time[0] + ":" + time[1] + " AM");
							}
						}
					} else {
						email.setTime(words[1] + " " + words[2] + ", " + words[5]);
					}
					maillist.add(email);
				}
			}
			emailFolder.close(false);
			emailStore.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return maillist;
	}

	@RequestMapping("/emailread")
	public ModelAndView emailread(HttpServletRequest request, @RequestParam String subject, String time,
			String recipient, CRMEmail mail) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			mail.setSubject(subject);
			mail.setTime(time);
			/*
			 * if(!recipient.equals("")) { mail.setFrom(recipient); }
			 * 
			 * System.out.println("recipient "+recipient);
			 */
			// mail.setSender(sender);
			return new ModelAndView("crm-emailRead", "mail", mail);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/sentemail")
	public ModelAndView sentemail(HttpServletRequest request, CRMEmail mail) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			List<CRMEmail> maillist = new ArrayList<CRMEmail>();
			final String username = "kavibharathi.natarajan@znifa.com";
			final String password = "God's daughter";
			String host = "poppro.zoho.com";
			try {
				Properties properties = new Properties();
				properties.setProperty("mail.pop3.host", host);
				properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				properties.setProperty("mail.pop3.socketFactory.fallback", "false");
				properties.setProperty("mail.pop3.port", "995");
				properties.setProperty("mail.pop3.socketFactory.port", "995");
				properties.put("mail.pop3.starttls.enable", "true");
				properties.put("mail.pop3.auth", "true");
				properties.put("mail.debug", "true");
				properties.put("mail.store.protocol", "pop3");
				properties.put("mail.transport.protocol", "pop3");
				properties.put("mail.debug.auth", "true");
				properties.setProperty("mail.pop3.socketFactory.fallback", "false");

				Session emailSession = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

				Store emailStore = emailSession.getStore("pop3");
				emailStore.connect(host, username, password);

				Folder emailFolder = emailStore.getFolder("INBOX");
				emailFolder.open(Folder.READ_ONLY);

				Message[] messages = emailFolder.getMessages();
				for (int j = messages.length - 1; j >= 0; j--) {
					CRMEmail email = new CRMEmail();
					Message message = messages[j];
					Address messagefrom = message.getFrom()[0];

					int first = String.valueOf(messagefrom).indexOf("<");
					int last = String.valueOf(messagefrom).indexOf(">");

					String result = "";
					if (first != -1 && last != -1) {
						result = String.valueOf(messagefrom).substring(first + 1, last);
					}

					if (username.equals(result)) {
						email.setSubject(message.getSubject());
						String str = String.valueOf(message.getFrom()[0]);
						str = String.valueOf(message.getFrom()[0]).replaceAll("\"", "");
						email.setFrom(str);
						// email.setContent(message.getContent().toString());

						String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
						String year[] = currentDate.split("-");
						String splitdate[] = year[2].split(":");
						String secondspliteddate[] = splitdate[0].split(" ");
						String monthString = new DateFormatSymbols().getMonths()[Integer.valueOf(year[1]) - 1];
						String month = monthString.substring(0, 3);
						String date = String.valueOf(message.getSentDate());
						String[] words = date.split(" ");

						if (Integer.valueOf(year[0]).equals(Integer.valueOf(words[5]))) {
							if (month != words[1] && year[2] != words[2]) {
								email.setTime(words[1] + " " + words[2]);
							}
							if (secondspliteddate[0].equals(words[2]) && month.equals(words[1])) {
								String[] time = words[3].split(":");

								if ((Integer.valueOf(time[0]) >= 12) && (Integer.valueOf(time[1]) >= 00)) {
									int totaltime = Integer.valueOf(time[0]) - 12;
									if (totaltime == 0) {
										totaltime = 12;
									}
									email.setTime(totaltime + ":" + time[1] + " PM");
								} else {
									email.setTime(time[0] + ":" + time[1] + " AM");
								}
							}
						} else {
							email.setTime(words[1] + " " + words[2] + ", " + words[5]);
						}

						maillist.add(email);

					}

				}
				emailFolder.close(false);
				emailStore.close();
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return new ModelAndView("crm-sentMail", "maillist", maillist);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/emailreadsent")
	public ModelAndView emailreadsent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			Date date = null;

			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.zoho.com");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.socketFactory.fallback", "false");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.debug", "true");
			properties.put("mail.store.protocol", "pop3");
			properties.put("mail.transport.protocol", "smtp");
			properties.put("mail.debug.auth", "true");
			properties.put("mail.pop3.socketFactory.fallback", "false");

			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("kavibharathi.natarajan@znifa.com", "God's daughter");
				}
			});

			// session.setDebug(true);
			try {
				// Get a Store object and connect to the current host
				Store store = session.getStore("pop3");
				store.connect("poppro.zoho.com", "kavibharathi.natarajan@znifa.com", "God's daughter");// change the
																										// user and
																										// password
																										// accordingly

				Folder folder = store.getFolder("inbox");
				if (!folder.exists()) {
					System.out.println("inbox not found");
					System.exit(0);
				}
				folder.open(Folder.READ_ONLY);

				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

				Message[] messages = folder.getMessages();
				if (messages.length != 0) {
					for (int i = 0, n = messages.length; i < n; i++) {
						Message message = messages[i];
						date = message.getSentDate();
						// Get all the information from the message
						String from = InternetAddress.toString(message.getFrom());
						if (from != null) {
							System.out.println("From: " + from);
						}
						String replyTo = InternetAddress.toString(message.getReplyTo());
						if (replyTo != null) {
							System.out.println("Reply-to: " + replyTo);
						}
						String to = InternetAddress.toString(message.getRecipients(Message.RecipientType.TO));
						if (to != null) {
							System.out.println("To: " + to);
						}

						String subject = message.getSubject();
						if (subject != null) {
							System.out.println("Subject: " + subject);
						}
						Date sent = message.getSentDate();
						if (sent != null) {
							System.out.println("Sent: " + sent);
						}

						Message message2 = new MimeMessage(session);
						message2 = (MimeMessage) message.reply(false);
						message2.setSubject("RE: " + message.getSubject());
						message2.setFrom(new InternetAddress(from));
						message2.setReplyTo(message.getReplyTo());

						message2.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

						// Create your new message part
						BodyPart messageBodyPart = new MimeBodyPart();
						messageBodyPart.setText("Oiginal message:nn");

						// Create a multi-part to combine the parts
						Multipart multipart = new MimeMultipart();
						multipart.addBodyPart(messageBodyPart);

						// Create and fill part for the forwarded content
						messageBodyPart = new MimeBodyPart();
						messageBodyPart.setDataHandler(message.getDataHandler());

						// Add part to multi part
						multipart.addBodyPart(messageBodyPart);

						// Associate multi-part with message
						message2.setContent(multipart);

						// Send message
						Transport.send(message2);

						System.out.println("message replied successfully ....");

					}
				}
			}

			catch (Exception e) {
				e.printStackTrace();
			}

			return new ModelAndView("crm-composemail", "crm-composeemail", null);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/emailsent")
	public ModelAndView emailsent(MultipartHttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CRMEmail mail) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			String content = mail.getContent();
			content = content.replaceAll("\\<.*?\\>", "");

			String sendermail = "kavibharathi.natarajan@znifa.com";

			Properties properties = new Properties();
			properties.setProperty("mail.smtp.host", "smtp.zoho.com");
			properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.setProperty("mail.smtp.socketFactory.fallback", "false");
			properties.setProperty("mail.smtp.port", "465");
			properties.setProperty("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.debug", "true");
			properties.put("mail.store.protocol", "pop3");
			properties.put("mail.transport.protocol", "smtp");
			properties.put("mail.debug.auth", "true");
			properties.setProperty("mail.pop3.socketFactory.fallback", "false");
			Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(sendermail, "God's daughter");
				}
			});
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress("kavibharathi.natarajan@znifa.com"));

				String[] recipients = mail.getRecipient().split(";");

				if (recipients != null && recipients.length > 0) {
					for (int i = 0; i < recipients.length; i++) {
						String recipient = recipients[i];
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
					}
				}
				String[] cc = null;
				if (mail.getCc() != null && !mail.getCc().equals("")) {
					cc = mail.getCc().split(";");
					if (cc != null && cc.length > 0) {
						for (int i = 0; i < cc.length; i++) {
							String Cc = cc[i];
							message.addRecipient(RecipientType.CC, new InternetAddress(Cc));
						}
					}
				}

				message.setSubject(mail.getSubject());
				message.setText(content);
				mail.setFrom(sendermail);
				String username = (String) request.getSession().getAttribute("loggedInUser");
				mail.setLoginname(username);
				mail.setTraceNo(mail.getTraceNo());

				CRMuserService.getsequencelist(mail);

				/*
				 * BodyPart messageBodyPart = new MimeBodyPart();
				 * messageBodyPart.setText(content);
				 * 
				 * Multipart multipart = new MimeMultipart();
				 * multipart.addBodyPart(messageBodyPart);
				 * 
				 * messageBodyPart = new MimeBodyPart();
				 * 
				 * 
				 * 
				 * String folder ="D:\\Projects\\CRM\\documents\\";
				 * 
				 * String saveDirectory = folder+mail.getTraceNo()+"\\";
				 * 
				 * String directory2 = saveDirectory + mail.getEmailSequenceNo();
				 * 
				 * File dirfile = new File(folder+mail.getTraceNo());
				 * 
				 * File file2 = new File(saveDirectory + mail.getEmailSequenceNo());
				 * 
				 * if (!dirfile.exists()) { if (dirfile.mkdir()) {
				 * System.out.println("Directory for Trace no is created!"); } if
				 * (!file2.exists()) { if(file2.mkdir()) {
				 * System.out.println("Directory for Email Sequence no is created!"); } } } else
				 * { if (!file2.exists()) { if(file2.mkdir()) {
				 * System.out.println("Directory for Email Sequence no is created!"); } }
				 * 
				 * 
				 * 
				 * } MultipartFile file = request.getFile("fileUpload");
				 * 
				 * if(!file.getOriginalFilename().equals("")) {
				 * FileCopyUtils.copy(file.getBytes(), new File(directory2 +
				 * file.getOriginalFilename())); InputStream inputStream = new
				 * ByteArrayInputStream(file.getBytes());
				 * 
				 * }
				 * 
				 * MultipartFile multipartFile = request.getFile("fileUpload");
				 * 
				 * String filename = multipartFile.getOriginalFilename(); DataSource source =
				 * new FileDataSource(filename); messageBodyPart.setDataHandler(new
				 * DataHandler(source)); messageBodyPart.setFileName(filename);
				 * multipart.addBodyPart(messageBodyPart);
				 * 
				 * message.setContent(multipart);
				 */

				Transport.send(message);

				CRMuserService.insertEMailDetails(mail);

				System.out.println("Sent message successfully....");

				List<CRMEmail> emailList = CRMuserService.getemailDetails(mail.getTraceNo());
				mail.setMailDetailsList(emailList);
			} catch (MessagingException e) {
				e.printStackTrace();
			}

			return new ModelAndView("crm-composemail", "mail", mail);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/composemail")
	public ModelAndView composeemail(HttpServletRequest request, @RequestParam String id, String email,
			@ModelAttribute CRMEmail mail) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			mail.setRecipient(email);
			mail.setTraceNo(id);

			List<CRMEmail> emailList = CRMuserService.getemailDetails(id);
			mail.setMailDetailsList(emailList);

			return new ModelAndView("crm-composemail", "mail", mail);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

}
