!function(e){"use strict";var i=function(){};i.prototype.init=function(){e("#basic-datepicker").flatpickr(),e("#fromdate").flatpickr({dateFormat: "m/d/Y", maxDate: "today"}),e("#todate").flatpickr({dateFormat: "m/d/Y", maxDate: "today"}),e("#followUpDate").flatpickr({dateFormat: "m/d/Y", minDate: "today"}),e("#appointmentDate").flatpickr({dateFormat: "m/d/Y", minDate: "today"}),e("#effectiveFrom").flatpickr({dateFormat: "m/d/Y"}),e("#effectiveTo").flatpickr({dateFormat: "m/d/Y"}),e("#appointmentTime").flatpickr({enableTime:!0,noCalendar:!0,dateFormat:"H:i"}),e("#check-minutes").click(function(i){i.stopPropagation(),e("#single-input").clockpicker("show").clockpicker("toggleView","minutes")})},e.FormPickers=new i,e.FormPickers.Constructor=i}(window.jQuery),function(e){"use strict";e.FormPickers.init()}(window.jQuery);