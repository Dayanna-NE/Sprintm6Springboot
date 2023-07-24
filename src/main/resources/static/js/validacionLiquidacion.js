$(document).ready(function () {
    // --------------------------- Al selecionar Trabajador------------------
    $('#trabajador2').change(function () {
        const opcionSelecionada = $(this).find('option:selected');
        const idSalud = opcionSelecionada.data('salud');
        const idPrevision = opcionSelecionada.data('prevision');
        console.log(idSalud);
        console.log(idPrevision);
        $('#institucionSalud2').val(idSalud).change();
        $('#institucionPrevision2').val(idPrevision).change();
        // ----------------------- Cambiando estados
        // --Sueldo Imponible
        $('#sueldoImponible')
            .removeAttr('readonly');
        $('#sueldoImponible')
            .removeClass('border-secondary bg-secondary');
        $('#isueldoImponible')
            .removeClass('bg-secondary border-secondary')
            .addClass('bg-danger border-border-danger');
        $('#dvsueldoImponible')
            .remove();
        // --insitucion salud
        $('#institucionSalud2')
            .removeClass('is-invalid')
            .addClass('is-valid border-primary');
        $('#institucionSaludAD')
            .removeClass('invalid-tooltip')
            .addClass('valid-tooltip bg-primary')
            .text('(Automatico) (✿◕‿◕)っ');
        // --institucion prevision
        $('#institucionPrevision2')
            .removeClass('is-invalid')
            .addClass('is-valid border-primary');
        $('#institucionPrevisionAD')
            .removeClass('invalid-tooltip')
            .addClass('valid-tooltip bg-primary')
            .text('(Automatico) (✿◕‿◕)っ');
        // --anticipo
        $('#anticipo')
            .removeClass('border-secondary')
            .removeAttr('readonly');
        $('#ianticipo')
            .removeClass('bg-secondary border-secondary')
            .addClass('bg-success border-success');
        $('#danticipo')
            .removeClass('bg-secondary')
            .text('(opcional) (✿◕‿◕)っ ');
        $('#sueldoImponible').trigger('input');

    });
    // --------------------------- Activando Select Salud -----------------
    $('#institucionSalud2').change(function (){
        const opcionSelecionada = $(this).find('option:selected');
        const saludDcto = opcionSelecionada.data('salud-dcto');
        console.log(saludDcto);
        $('#montoInstSalud').val(saludDcto+'%');
        $('#salud').val(saludDcto/100);



    });
    // ------------------------ Activando Select Prevision -----------
    $('#institucionPrevision2').change(function () {
        const opcionSelecionada = $(this).find('option:selected');
        const previsionDcto = opcionSelecionada.data('prevision-dcto');
        console.log(previsionDcto);
        $('#montoInstPrevisional').val(previsionDcto+'%');
        $('#prevision').val(previsionDcto/100);

    });
    // -------------------------------- Suelo Inponible ---------------------
    $('#sueldoImponible').on('input',function (){
        controlarLongitudCaracteres('#sueldoImponible',11,0);
        formatoMoneda(this);
        let longitudSueldoImponible =formatoEntero($(this).val()).length;
        if(longitudSueldoImponible>0){
            $('#isueldoImponible')
                .removeClass('bg-danger border-danger')
                .addClass('bg-success border-border-success');
            $('#disueldoImponible')
                .removeClass('invalid-tooltip')
                .addClass('valid-tooltip');
            if (Math.floor(longitudSueldoImponible % 2) ===0){
                $('#disueldoImponible')
                    .text("(✿◕‿◕)~Ingresando sueldo ");
            }else{
                $('#disueldoImponible')
                    .text("~(◕‿◕✿) Ingresando sueldo ");
            }

            //----->Activando casillas automaticas
            cambioEstilosOn('montoInstSalud');
            cambioEstilosOn('montoInstPrevisional');
            cambioEstilosOn('totalDescuento');
            cambioEstilosOn('totalHaberes');
            cambioEstilosOn('sueldoLiquido');
            // (Automatico) (✿◕‿◕)っ  (Automatico)(✿◡‿◡)
        }
        else{
            $('#isueldoImponible')
                .removeClass('bg-success border-success')
                .addClass('bg-danger border-border-danger');
            $('#disueldoImponible')
                .removeClass('valid-tooltip')
                .addClass('invalid-tooltip')
                .text('(͠◉_◉᷅ ) Ingrese el sueldo. ');
            //--->Desactivando casillas automaticas
            cambioEstilosOff('montoInstSalud');
            cambioEstilosOff('montoInstPrevisional');
            cambioEstilosOff('totalDescuento');
            cambioEstilosOff('totalHaberes');
            cambioEstilosOff('sueldoLiquido');
            // (Automatico) (✿◕‿◕)っ  (Automatico)(✿◡‿◡)
        }
        // --------------------------- Declaracion Variables
        const salud=$('#salud').val();
        const prevision = $('#prevision').val();
        console.log("constante Salud: "+salud);
        console.log("constante prevision"+prevision);
        let montoSalud=0.0;
        let montoPrevicion=0.0;
        let totalDescuento=0.0;
        let totalHaberes=0;
        let sueldoLiquido;
        let sueldoInponible = formatoEntero($(this).val());
        console.log(sueldoInponible)
        console.log(montoSalud);
        console.log(montoPrevicion);
        console.log(totalDescuento);
        console.log(totalHaberes);
        console.log(sueldoInponible);
        // ----------------------------------Iniciando Operaciones----------------
        montoSalud= Math.round(sueldoInponible * salud);
        montoPrevicion= Math.round(sueldoInponible * prevision);
        totalDescuento = Math.round(montoSalud + montoPrevicion);
        totalHaberes= Math.round(sueldoInponible - totalDescuento);
        sueldoLiquido = Math.round(totalHaberes);
        console.log("monto inponible: "+sueldoInponible)
        console.log("monto salud: "+ Math.round(montoSalud) );
        console.log("monto afp: "+ Math.round(montoPrevicion));
        console.log("Total Descuento: "+ Math.round(totalDescuento));
        console.log("Total Haberes: "+ Math.round(totalHaberes));
        console.log("Sueldo Liquido: "+Math.round(sueldoLiquido));
        // ------------------------- Entregando valores
        $('#montoInstSalud').val(montoSalud);
        $('#montoInstPrevisional').val(montoPrevicion);
        $('#totalDescuento').val(totalDescuento);
        $('#totalHaberes').val(totalHaberes);
        $('#sueldoLiquido').val(sueldoLiquido);
        actualizarValuePorName('sueldoImponible',sueldoInponible);
        actualizarValuePorName('sueldoLiquido',sueldoLiquido);
        actualizarValuePorName('montoInstSalud',montoPrevicion);
        actualizarValuePorName('montoInstPrevisional',montoPrevicion);
        actualizarValuePorName('totalDescuento',totalDescuento);
        actualizarValuePorName('totalHaberes',totalHaberes);
        actualizarValuePorName('anticipo',0);
        //------ verificando valores
        retornarValudePorName('sueldoImponible');
        retornarValudePorName('sueldoLiquido');
        retornarValudePorName('montoInstSalud');
        retornarValudePorName('montoInstPrevisional');
        retornarValudePorName('totalDescuento');
        retornarValudePorName('totalHaberes');
        retornarValudePorName('anticipo');
        console.log("Institucion prevision: "+ $('#institucionPrevision2').val());
        // ---> Activando Formato Moneda
        $('#montoInstSalud').trigger('input');

    });
    // ---> Activando Formato Moneda <-----
    $('#montoInstSalud').on('input', function() {
        formatoMoneda(this);
        $('#montoInstPrevisional').trigger('input');
    });
    // ---> Activando Formato Moneda <-----
    $('#montoInstPrevisional').on('input', function() {
        formatoMoneda(this);
        $('#totalDescuento').trigger('input');
    });
    // ---> Activando Formato Moneda <-----
    $('#totalDescuento').on('input', function() {
        formatoMoneda(this);
        $('#totalHaberes').trigger('input');
    });
    // ---> Activando Formato Moneda <-----
    $('#totalHaberes').on('input', function() {
        formatoMoneda(this);
        $('#anticipo').trigger('input');

    });


    //-------------------------Anticipo -------------------------------
    $('#anticipo').on('input',function () {
        controlarLongitudCaracteres('#anticipo',11,0);
        formatoMoneda(this);
        let anticipo = formatoEntero($(this).val());
        let sueldoTotal = formatoEntero($('#totalHaberes').val());
        sueldoTotal = sueldoTotal - anticipo;
        $('#sueldoLiquido').val(sueldoTotal);
        actualizarValuePorName('anticipo',anticipo);
        actualizarValuePorName('sueldoLiquido',sueldoTotal);
        let longitudAnticipo = anticipo.length;
        if (longitudAnticipo % 2 ===0){
            $('#danticipo')
                .text('(✿◕‿◕)~ Ingresando anticipo. ');
        }else{
            $('#danticipo')
                .text('~(◕‿◕✿) Ingresando anticipo. ');
        }
        // ---> Activando Formato Moneda <-----
        $('#sueldoLiquido').trigger('input');
    });
    // ---> Activando Formato Moneda <-----
    $('#sueldoLiquido').on('input', function() {
        formatoMoneda(this);
    });
    // --------------------------transformar a moneda------------------------------//
        function formatoMoneda(input) {
        // Eliminamos cualquier carácter no numérico que no sea el punto decimal
        let valor = input.value.replace(/[^\d-]/g, '');

        // Si el valor comienza con un punto decimal, lo reemplazamos por 0.
        if (valor.startsWith('.')||valor.startsWith('0')) {
        valor = '' ;
    }

        // Dividimos el valor en parte entera y decimal, si aplica.
        let partes = valor.split('.');
        let parteEntera = partes[0];
        let parteDecimal = partes.length > 1 ? '.' + partes[1] : '';

        // Añadimos un separador de miles a la parte entera.
        parteEntera = parteEntera.replace(/\B(?=(\d{3})+(?!\d))/g, ',');

        // Limitamos el número de decimales a dos.
        parteDecimal = ' '+parteDecimal.slice(0, 3);
        input.value= parteEntera;
        // Unimos las partes y mostramos el resultado en el input.
        //input.value = '$' + parteEntera + parteDecimal;
    }



    // ------------------------------------- formato entero --------------------------------//
    function formatoEntero(valor){
        //eliminamos cualquier caracter que no sea numero
        return valor.replace(/[^0-9]+/g, '');
    }
    // ------------------------------------- Automatizacion de cambio de estilos -------------//
    function cambioEstilosOn(input){
        $('#'+input)
            .removeClass('border-secondary')
            .addClass('border-primary');
        $('#i'+input)
            .removeClass('bg-secondary border-secondary')
            .addClass('bg-primary border-primary');
        $('#d'+input)
            .removeClass('bg-secondary')
            .addClass('bg-primary')
            .text('(Automatico) (✿◕‿◕)っ');
    }
    function cambioEstilosOff(input){
        $('#'+input)
            .removeClass('border-primary')
            .addClass('border-secondary');
        $('#i'+input)
            .removeClass('bg-primary border-primary')
            .addClass('bg-secondary border-secondary');
        $('#d'+input)
            .removeClass('bg-primary')
            .addClass('bg-secondary')
            .text('(Automatico)(✿◡‿◡)');
    }
    $('#registrarTrabajador').click(function () {
       $('#institucionPrevision2')
           .removeAttr('disabled');
       $('#institucionSalud2')
           .removeAttr('disabled');
        $('#trabajador2')
            .removeAttr('disabled');
    });
    function actualizarValuePorName(nombre,valor) {
        $('input[name="'+nombre+'"]').val(valor);
    }
    function retornarValudePorName(nombre) {
        return console.log(nombre +': '+$('input[name="'+nombre+'"]').val());
    }
});