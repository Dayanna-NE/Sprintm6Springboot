function controlarLongitudCaracteres(idInput='#run', logitudMax=8, LongitudMin=0){
    var input = $(idInput).val();
    if(input.length > logitudMax){
        console.log("entrando al if");
        var nuevoVal= input.substring(0,logitudMax);
        $(idInput).val(nuevoVal);
    }
}
function controlarSoloNumero(idInput ='#run') {
    var input = $(idInput).val();
    var regex = /^\d*$/;
    if (!regex.test(input)) {
        $(idInput).val( input.replace(/\D/g, ''));
    }
}
$(document).ready(function() {
    // Asignar el evento input al input RUN
    $('#run').on('input', function() {
        controlarLongitudCaracteres("#run", 8);
        controlarSoloNumero("#run");
    });

});