$(document).ready(function () {
    
    /**
     * Function that resizes the screen
     * 
     * @returns {undefined}
     */
    function resizeScreen() {
        $("body").css("padding-top", parseInt($("nav.navbar.fixed-top").css("height")));
    }
    
    resizeScreen();
    $(window).resize(function() {
        resizeScreen();
    });
    
    $("nav.navbar.fixed-top").on('shown.bs.collapse', function () {
        resizeScreen();
    });
    
    $("nav.navbar.fixed-top").on('hidden.bs.collapse', function () {
        resizeScreen();
    });
    
    
});