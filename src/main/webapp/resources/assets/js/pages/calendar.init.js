$(document).ready(function() {


            var calendar = $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                selectable: true,
                selectHelper: true,
                displayEventTime: false,
                defaultTimedEventDuration: '01:00:00',
                //slotDuration: '00:30:00',
                editable: true,
                events:function(start, end, timezone, callback) {
                    $.ajax({
                        url: 'noteList',
                        success: function(result) {
                            var calendarlist = [];
                            
                            for(var i=0; i<result.length; i++)
	         	    	    {
	         	    			   	startdate = result[i].fullTime ;
	         	    			   	appointmentWith = result[i].appointmentWith ;
	         	    			   	appointmentTime = result[i].appointmentTime ;
	         	    			   	notes = result[i].notes;
	         	    			   	traceno = result[i].traceNo;
	         	    			   	notesid = result[i].notesId;
	         	    			   	notesstatus = result[i].appointmentStatus;
	         	    			   	time = result[i].calendarTime;
	         	    			   	ampm = result[i].ampm;	
	         	    			   	calDate = result[i].calendarDate;	
	         	    			   	name =  result[i].loginName;         	    			   
	         	    			   	//fulltime = result[i].fullTime;
	         	    			   	
	         	    			   	var button="bg-warning";
	         	    			   	if(notesstatus == 'Closed')
	         	    			   	{
	         	    			   		button = "bg-success"
	         	    			   	}
	         	    			   	if(notesstatus == 'Reschedule')
	         	    			   	{
	         	    			   		button = "bg-primary"
	         	    			   	}
	         	    				if(notesstatus == 'Cancel')
	         	    				{
	         	    					button = "bg-info"
	         	    				}
	         	    			   	
	         	    			   var formattedstartdate = calDate+"T";
	         	    			   
	         	    			  var appttimetemp ='';
	         	    			  var appthour = '';
	         	    			  var apptseconds = '';	         	    			    
	         	    			  var appttime = '';
	         	    			    
	         	    			   if(time != null)
	         	    				{
	         	    					appttimetemp = time.split(":");
		         	    			    appthour = appttimetemp[0];
		         	    			    apptseconds = appttimetemp[1];		         	    			    
		         	    			    appttime = appthour+":"+apptseconds;
	         	    				}
	         	    			   
	         	    			   
	         	    			 /* var ampm = "AM IST";
		         	    			 if( appthour > 12 ) {
		         	    				appthour -= 12;
		         	    			     ampm = "PM IST";
		         	    			 }*/
	         	    			   	
		         	    			appttime = appttime + " " + ampm;
	         	    			   	calendarlist.push({ 
	                                     "title" : appttime + " - Meeting with "+ appointmentWith,                          
	                                     "start"  : startdate,
	                                     "className" : button,
	                                     "notes" : notes,
	                                     "traceNo" : traceno,
	                                     "time" : appttime,
	                                     "id" : notesid,
	                                     "aptstatus" : notesstatus,
	                                     "name" :name,

	                                 });
	
	         	    	     }
                            
                     
                           // alert("calendarlist "+ calendarlist);
                            callback(calendarlist);
                        }
                    });
                },
                eventMouseover: function (data, event, view) {

                    tooltip = '<div class="tooltiptopicevent" style="width:auto;height:auto;background:#7BF4AD;position:absolute;z-index:10001;padding:10px 10px 10px 10px ;  line-height: 200%;">' + 'Title ' + ': ' + data.title + '</br>' + 'Appointment Date: ' + ': ' + data.start.format('MM/DD/YYYY') + '</div>';


                    $("body").append(tooltip);
                    $(this).mouseover(function (e) {
                        $(this).css('z-index', 10000);
                        $('.tooltiptopicevent').fadeIn('500');
                        $('.tooltiptopicevent').fadeTo('10', 1.9);
                    }).mousemove(function (e) {
                        $('.tooltiptopicevent').css('top', e.pageY + 10);
                        $('.tooltiptopicevent').css('left', e.pageX + 20);
                    });


                },
                eventMouseout: function (data, event, view) {
                    $(this).css('z-index', 8);
                    $('.tooltiptopicevent').remove();
                },
                eventClick:  function(event, jsEvent, view) {
                	var eventstart = event.start;
                	var notesstatus = event.aptstatus;
                	//alert("notes status "+ notesstatus);
                    $('#modalTitle').html(event.title);
                    $('#traceNo').html(event.traceNo);
                    $('#modalBody').html(event.notes);                   
                    $('#aptDate').html(eventstart.format('MM/DD/YYYY'));
                    $('#atime').html(event.time);
                    $('#hiddenid').val(event.id);
                    $('#notesstatus').val(event.aptstatus);
                    $('#userName').html(event.name);
                    $('#calendarModal').modal();
                    
                    if(notesstatus != '')
                	{
                    	//document.getElementById('btnsave').disabled = true;
                    	document.getElementById('btnsave').style.visibility = 'hidden';
                	}
                    else
                    {
                    	document.getElementById('btnsave').style.visibility = 'visible';
                    }
                },
                dayClick: function(date, allDay, jsEvent, view) {
                	$('#calendar').fullCalendar( 'changeView', 'agendaDay');
                	$('#calendar').fullCalendar( 'gotoDate', date.format()  );
                	},

                
            });
   
        });