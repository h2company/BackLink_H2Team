window.blSiteSettings = {
	"features" : null,
	"site_id" : "[[${siteId}]]",
	"ip" : "[[${ip}]]",
	"userAgent" : "[[${userAgent}]]",
	"integrations" : {
		"optimizely" : {
			"tag_recordings" : false
		}
	},
	"privacy_policy_url" : "",
	"state_change_listen_mode" : "automatic",
	"anonymize_digits" : false,
	"recording_capture_keystrokes" : false,
	"rec_value" : 1,
	"suppress_text" : false,
	"suppress_location" : false,
	"record" : true,
	"r" : 1,
	"suppress_all" : false,
	"anonymize_emails" : true,
	"testing_data" : null,
	"testing_id" : "",
	"testing_device" : "",
	"preview" : true
};
(function(b, d, w) {
	f = d.getElementsByTagName('body')[0];
	s = d.createElement('iframe');
	s.name = "_blRemoteVarsFrame", s.title = "_blRemoteVarsFrame", s.async = 1;
	s.src = w;
	s.width = 0;
	s.height = 0;
	s.onload = function() {
		d.getElementsByName('_blRemoteVarsFrame')[0].contentWindow.postMessage({
			'action': 'connect',
			'blSiteSettings': blSiteSettings
		}, '*');
	}
	f.appendChild(s);
	d.addEventListener("click", function(event){
		console.log(event);
		d.getElementsByName('_blRemoteVarsFrame')[0].contentWindow.postMessage({
			'action': 'tracking',
			'ip': blSiteSettings.ip,
			'userAgent': blSiteSettings.userAgent,
			'timeAccess': new Date().getTime(),
			'events' : [
				{
					'siteId': blSiteSettings.site_id,
					'uuid': 0,
					'eventAction' : {
						'timeStamp': new Date().getTime(),
						'selector' : event.toElement.outerHTML+'|dataset:'+JSON.stringify(event.toElement.dataset),
						'event' : 'mouse_click'
					}
				}
			]
		}, '*');
	});
})(window, document, 'http://localhost:8082/frame/box.html');