/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('#slika').click(function () {
        $('.slikaFajl').click();
    });

    function procitajURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('.slika_prikaz').attr('src', e.target.result);
                $('.slika_prikaz').css('display', 'inline-block');
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $('.slikaFajl').change(function(){
        procitajURL(this);
    });

});

function sakrijPrikaz(){
    $('.slika_prikaz').css('display', 'none');
}

