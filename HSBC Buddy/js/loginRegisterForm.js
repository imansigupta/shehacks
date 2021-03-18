$(document).ready(function() {
    $('.cta-login').on('click', function() {
        $('#loginForm, .formwrap').addClass('active');
        $("body").toggleClass("left", 100);
       
        $('.icon-close').addClass('open');
        // $("body").toggleClass("left", 100);
        $('.cta-login').addClass("colorChange");
    });

    
    $('.icon-close').on('click', function() {
        $('.toggle-form, .formwrap').removeClass('active');
        $('.icon-close').removeClass('open');
        $("body").toggleClass("left", 100);
        
        $('.cta-login').removeClass("colorChange");

    });
});