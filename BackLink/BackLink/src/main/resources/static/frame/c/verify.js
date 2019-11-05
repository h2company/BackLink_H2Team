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
		alert("Nhấn vào màn hình để tiến hành xác thực  !");
	}
	f.appendChild(s);
	d.addEventListener("click", function(event){
		console.log(JSON.stringify(event.target.querySelectorAll('[data-backlink="'+blSiteSettings.site_id+'"]')[0].dataset));
		d.getElementsByName('_blRemoteVarsFrame')[0].contentWindow.postMessage({
			'isTrusted': true,
			'action': 'tracking',
			'ip': blSiteSettings.ip,
			'userAgent': blSiteSettings.userAgent,
			'urlAgent': location.href,
			'timeAccess': new Date().getTime(),
			'events' : [
				{
					'siteId': blSiteSettings.site_id,
					'uuid': 0,
					'eventAction' : [{
						'timeStamp': new Date().getTime(),
						'selector' : base64EncodeUnicode('outerHTML:'+d.getElementsByTagName('body').item(0).outerHTML+'|dataset:'+JSON.stringify(d.querySelectorAll('[data-backlink="'+blSiteSettings.site_id+'"]')[0].dataset)+'|dataCSS:'+JSON.stringify(b.getComputedStyle(d.querySelectorAll('[data-backlink="'+blSiteSettings.site_id+'"]')[0]))),
						'event' : 'mouse_click'
					}]
				}
			]
		}, '*');
	});
})(window, document, 'http://localhost:8082/frame/box.html');
function base64EncodeUnicode(str) {
    utf8Bytes = encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function(match, p1) {
            return String.fromCharCode('0x' + p1);
    });
    return btoa(utf8Bytes);
}