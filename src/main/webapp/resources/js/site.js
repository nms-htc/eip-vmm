/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 * All rights reserved.
 */

$(function() {  
    var pull        = $('#pull');  
        menu        = $('nav#mainNav>ul');  
        menuHeight  = menu.height();  
  
    $(pull).on('click', function(e) {  
        e.preventDefault();  
        menu.slideToggle();  
    });  
});

$(window).resize(function(){  
    var w = $(window).width();  
        
    if(w > 767 && menu.is(':hidden')) {  
         menu.removeAttr('style'); 
    }  
});  