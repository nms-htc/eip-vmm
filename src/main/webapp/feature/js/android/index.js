document.addEventListener('DOMContentLoaded', function(){
	function echoNavLi(n){var html='';for(i=1;i<n;i++){html+="<li></li>"}return html;}

	var changImg = ai.i("change_img"),
		change_img_nav_ul = ai.i("change_img_nav_ul"),
		change_img_li = ai.a("#change_img > li"),
		changImgLength = change_img_li.length,
		nav_li=echoNavLi(changImgLength),
		img_width  = 320;

	if(ai.hv()){
		var img_width  = ai.ww();
		var img_height = img_width/(31/12);
		var arr = Array.prototype.slice.call(ai.a("#change_img > li"));
		for(var i= 0,l= changImgLength; i < l; i++ ){
			var change_img_li_now = change_img_li[i];
			change_img_li_now.style.width  = img_width + "px";
			change_img_li_now.style.height = img_height + "px";
		}
		var ui_header_slideWrap = ai.i("ui-header-slideWrap");
		ui_header_slideWrap.style.width  = img_width + "px";
		ui_header_slideWrap.style.height = img_height + "px";
	}
	changImg.style.width=''+changImgLength*img_width+'px';
	change_img_nav_ul.innerHTML='<li class="active"></li>'+nav_li+'';
	function changeScreenEndFun() {
		document.querySelector('#change_img_nav_ul > li.active').className = '';
		ai.a('#change_img_nav_ul > li')[this.page].className = 'active';
	}
	slip('page',changImg,{
	change_time: 4000,
			num: changImgLength,
	  no_follow: true,
		 endFun: changeScreenEndFun
	});

}, false);