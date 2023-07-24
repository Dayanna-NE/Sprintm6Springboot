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
        $('#sueldoImponible').removeAttr('readonly');
        $('#sueldoImponible').removeClass('border-secondary bg-secondary');
        $('#isueldoImponible').removeClass('bg-secondary border-secondary').addClass('bg-danger border-border-danger');
        $('#dvsueldoImponible').remove();
        // --insitucion salud
        $('#institucionSalud2').removeClass('is-invalid').addClass('is-valid border-primary');
        $('#institucionSaludAD').removeClass('invalid-tooltip').addClass('valid-tooltip bg-primary').text('(Automatico) (✿◕‿◕)っ');
        // --institucion prevision
        $('#institucionPrevision2').removeClass('is-invalid').addClass('is-valid border-primary');
        $('#institucionPrevisionAD').removeClass('invalid-tooltip').addClass('valid-tooltip bg-primary').text('(Automatico) (✿◕‿◕)っ');

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
        formatoMoneda(this);
        if($(this).val().length >0){
            $('#isueldoImponible')
                .removeClass('bg-danger border-danger')
                .addClass('bg-success border-border-success');
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
            //--->Desactivando casillas automaticas
            cambioEstilosOff('imontoInstSalud');
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
        console.log(montoSalud);
        console.log(montoPrevicion);
        console.log(totalDescuento);
        console.log(totalHaberes);
        console.log(sueldoInponible);
        // ----------------------------------Iniciando Operaciones----------------
        montoSalud= sueldoInponible * salud;
        montoPrevicion= sueldoInponible * prevision;
        totalDescuento = montoSalud + montoPrevicion;
        totalHaberes= sueldoInponible - totalDescuento;
        sueldoLiquido = totalHaberes;

        console.log("monto salud: "+montoSalud);
        console.log("monto afp: "+montoPrevicion);
        console.log(totalDescuento);
        console.log(totalHaberes);

        // ------------------------- Entregando valores
        $('#montoInstSalud').val(montoSalud);
        $('#montoInstPrevisional').val(montoPrevicion);
        $('#totalDescuento').val(totalDescuento);
        $('#totalHaberes').val(totalHaberes);
        $('#sueldoLiquido').val(sueldoLiquido);


    });
    //-------------------------Anticipo -------------------------------
    $('#anticipo').on('input',function () {

        let anticipo = formatoEntero($(this).val());
        let sueldoTotal = formatoEntero($('#totalHaberes').val());
        sueldoTotal = sueldoTotal - anticipo;
        $('#sueldoLiquido').val(sueldoTotal);

    });

    // --------------------------transformar a moneda------------------------------//
    function formatoMoneda(input) {
        // Eliminamos cualquier carácter no numérico que no sea el punto decimal
        let valor = input.value.replace(/[^\d.]/g, '');

        // Si el valor comienza con un punto decimal, lo reemplazamos por 0.
        if (valor.startsWith('.')) {
            valor = '0' + valor;
        }

        // Dividimos el valor en parte entera y decimal, si aplica.
        let partes = valor.split('.');
        let parteEntera = partes[0];
        let parteDecimal = partes.length > 1 ? '.' + partes[1] : '';

        // Añadimos un separador de miles a la parte entera.
        parteEntera = parteEntera.replace(/\B(?=(\d{3})+(?!\d))/g, ',');

        // Limitamos el número de decimales a dos.
        parteDecimal = parteDecimal.slice(0, 3);
        input.value = ''+ parteEntera + parteDecimal;
        // Unimos las partes y mostramos el resultado en el input.
        //input.value = '$' + parteEntera + parteDecimal;
    }
    // ------------------------------------- formato entero --------------------------------//
    function formatoEntero(valor){
        //eliminamos cualquier caracter que no sea numero
        return valor.replace(/[^0-9.]+/g, '');
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

});