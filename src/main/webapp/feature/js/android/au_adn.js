$(function(){
$("#js_tb_slideUp").delay(3000).slideUp(3000);
$("#js_tb_slideDown").delay(3000).slideDown(3000);
$(".js_tb_close").click(function(){
$(".ui-topBanner-info").fadeOut(600);});

$(".js_searchTog").toggle(function(){
$(".js_searchTogarea").show();},function(){
$(".js_searchTogarea").hide();});
var $ngtag_item=$(".js_ngtab_tag").children();
$ngtag_item.click(function(){
var ngtabIndex=$(this).parent().children().index(this);
var $ngtabArea=$(this).parent().parent().next();
$ngtabArea.children().eq(ngtabIndex).removeClass("ms-none").addClass("am_fadeout").siblings().addClass("ms-none");});
$(".js_disinfoTag_a").click(function(){
$(this).hide();
$(".js_disInfo_i").hide();
$(".js_disInfo_a").show();
$(".js_disinfoTag_i").show();
return false;});
$(".js_disinfoTag_i").click(function(){
$(this).hide();
$(".js_disInfo_a").hide();
$(".js_disInfo_i").show();
$(".js_disinfoTag_a").show();
return false;});

$(".js_usTog").toggle(function(){
$("#kw_form").show();},function(){
$("#kw_form").hide();
});   
$(".js_ups").delay(10000).slideUp(1000);

$(".js_pptClose").click(function(){
$(".js_autoIMG").hide();});
/*
$(".js_auto_w").each(function(){
var spLen=$(this).find("li").length;
var spWit=spLen*95+20;
$(this).width(spWit);});*/
$(".g_com").click(function(){
var parent=$(this).parent().parent();
$(parent).hide();
$(parent).siblings().show();
return false;});
$(".starArea li").each(function(){
$(this).mouseover(function(){
var index=$(".starArea li").index(this);
var index2=index+1;
$(".starArea div").width(40*index2);
return false;}).click(function(){
var index=$(".starArea li").index(this);
var index2=index+1;
$(".starArea div").width(40*index2);

$("#score").val(index2);
$(".starArea li").unbind("mouseout",FUN);
return false;});});
$(".starArea li").bind("mouseout",FUN=function(){
var index=$(".starArea li").index(this);
var index2=index+1;
$(".starArea div").width(0);
return false;});});
$(function(){
$(".ui-nav ul li a").click(function(){
var href=$(this).attr("href");
window.location.href=href;
return false;})
$(".js_close").click(function(){
$(this).fadeOut(600);});
$(".js-roundImg").each(function(){
$(this).wrap(function(){
return '<span class="roundImg" style="background:url('+$(this/index.htm).attr('src')+') no-repeat center center; width: '+$(this).width()+'px; height: '+$(this).height()+'px; background-size:'+$(this).width()+'px;"></span>';});
$(this).css("opacity","0");});
var $tabtogItem=$(".js_tab_tog").children();
$tabtogItem.click(function(){
$(this).addClass("on").siblings().removeClass("on");});
var $navtagItem=$(".js_nav_tag").children();
$navtagItem.click(function(){
$(this).children().addClass("on");
$(this).siblings().children().removeClass("on");});
$(".circle").each(function(){
this.src="http://image-gj.9game.com/2013/1/8/9013085.gif";});});
function com_toggle(){
var comArea=document.getElementById("g_comAera");
if(comArea.style.display=="block"){
comArea.style.display="none";}
else{
comArea.style.display="block";}}
/* 10.22新增 */
$(".js_auto_w").each(function(){
var spLen=$(this).find("li").length;
var spWit=spLen*($(this).find("li").eq(0).width()+4);
$(this).width(spWit);});

$(function(){
$(".ui-nav ul li a").click(function(){
var href=$(this).attr("href");
window.location.href=href;
return false;})
$(".js_close").click(function(){
$(this).fadeOut(600);});
$(".js-roundImg").each(function(){
$(this).wrap(function(){
return '<span class="roundImg" style="background:url('+$(this/index.htm).attr('src')+') no-repeat center center; width: '+$(this).width()+'px; height: '+$(this).height()+'px; background-size:'+$(this).width()+'px;"></span>';});
$(this).css("opacity","0");});
var $tabtogItem=$(".js_tab_tog").children();
$tabtogItem.click(function(){
$(this).addClass("on").siblings().removeClass("on");});
var $navtagItem=$(".js_nav_tag").children();
$navtagItem.click(function(){
$(this).children().addClass("on");
$(this).siblings().children().removeClass("on");});
$(".circle").each(function(){
this.src="http://image-gj.9game.com/2013/1/8/9013085.gif";});});
function com_toggle(){
var comArea=document.getElementById("g_comAera");
if(comArea.style.display=="block"){
comArea.style.display="none";}
else{
comArea.style.display="block";}}