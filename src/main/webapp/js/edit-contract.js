$(document).ready(function f() {
    $('.option_chb input:checkbox').each(function(i){
        $cur_pos_options = $("#tariff_select option:selected").data("options");
        $cur_pos_options_arr = $cur_pos_options.split(' ');
        if ($.inArray($(this).val(), $cur_pos_options_arr) !== -1){
            $(this).parent().removeClass("hidden");
        } else {
            $(this).parent().addClass("hidden");
        }

        var $req_options = $(this).data("req");
        var $options_arr = $req_options.split(' ');
        var $inc_options = $(this).data("inc");
        var $inc_options_arr = $inc_options.split(' ');

        var $current_id = $(this).val();
        if (this.checked){
            $('.option_chb input:checkbox').each(function(){
                if ($.inArray($(this).val(), $options_arr) !== -1){
                    if (this.disabled) {
                        $('.option_chb input:checkbox[value="'+$current_id+'"]').prop("checked", false);
                        return false;
                    }
                    $(this).trigger('click');
                    $(this).prop("checked", true);
                    $(this).prop("disabled", true);
                    $(this).addClass("by_"+$current_id);
                    $('#hidden_'+$(this).val()).prop("disabled", false);
                }
                if ($.inArray($(this).val(), $inc_options_arr) !== -1){
                    $(this).prop("disabled", true);
                    $(this).addClass("inc_by_"+$current_id);
                }
            })
        } else {
            $('.option_chb input:checkbox').each(function(){
                if ($.inArray($(this).val(), $options_arr) !== -1){
                    $(this).removeClass("by_"+$current_id);
                    $className = $(this).attr('class').split(' ');
                    if ($className.length == 1) {
                        $(this).prop("disabled", false);
                        $('#hidden_'+$(this).val()).prop("disabled", true);
                    }
                    $(this).trigger('click');
                }
                if ($.inArray($(this).val(), $inc_options_arr)){
                    $(this).removeClass("inc_by_"+$current_id);
                    $className = $(this).attr('class').split(' ');
                    if ($className.length == 1) {
                        $(this).prop("disabled", false);
                    }
                }
            })
        }
    }).on('click', function() {
        var $req_options = $(this).data("req");
        var $options_arr = $req_options.split(' ');
        var $inc_options = $(this).data("inc");
        var $inc_options_arr = $inc_options.split(' ');

        var $current_id = $(this).val();
        if (this.checked){
            $('.option_chb input:checkbox').each(function(){
                if ($.inArray($(this).val(), $options_arr) !== -1){
                    if (this.disabled) {
                        $('.option_chb input:checkbox[value="'+$current_id+'"]').prop("checked", false);
                        return false;
                    }
                    $(this).trigger('click');
                    $(this).prop("checked", true);
                    $(this).prop("disabled", true);
                    $(this).addClass("by_"+$current_id);
                    $('#hidden_'+$(this).val()).prop("disabled", false);
                }
                if ($.inArray($(this).val(), $inc_options_arr) !== -1){
                    $(this).prop("disabled", true);
                    $(this).addClass("inc_by_"+$current_id);
                }
            })
        } else {
            $('.option_chb input:checkbox').each(function(){
                if ($.inArray($(this).val(), $options_arr) !== -1){
                    $(this).removeClass("by_"+$current_id);
                    $className = $(this).attr('class').split(' ');
                    if ($className.length == 1) {
                        $(this).prop("disabled", false);
                        $('#hidden_'+$(this).val()).prop("disabled", true);
                    }
                    $(this).trigger('click');
                }
                if ($.inArray($(this).val(), $inc_options_arr)){
                    $(this).removeClass("inc_by_"+$current_id);
                    $className = $(this).attr('class').split(' ');
                    if ($className.length == 1) {
                        $(this).prop("disabled", false);
                    }
                }
            })
        }
    })
    $('#tariff_select').change(function () {
        var $pos_options = $("#tariff_select option:selected").data("options");
        var $options_arr = $pos_options.split(' ');
        if ($options_arr.length == 1){
            $('#text-empty-option').removeClass("hidden");
        } else {
            $('#text-empty-option').addClass("hidden");
        }
        $('.option_chb input:checkbox').each(function(i){
            $(this).prop("disabled", false).prop('checked', false).prop("class","depended_option");
            if ($.inArray($(this).val(), $options_arr) !== -1){
                $(this).parent().removeClass("hidden");
            } else {
                $(this).parent().addClass("hidden");
            }
        })
    });

});
