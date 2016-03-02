$(document).ready(function () {
    $('.option_chb input:checkbox').each(function(i){
        $cur_pos_options = $("#tariff_select option:selected").data("options");
        $cur_pos_options_arr = $cur_pos_options.split(' ');
        if ($.inArray($(this).val(), $cur_pos_options_arr) !== -1){
            $(this).parent().removeClass("hidden");
        } else {
            $(this).parent().addClass("hidden");
        }

        var $cur_req_options = $(this).data("req");
        var $cur_options_arr = $cur_req_options.split(' ');
        if (this.checked){
            $current_id = $(this).val();
            $('.option_chb input:checkbox').each(function(){
                if ($.inArray($(this).val(), $cur_options_arr) !== -1){
                    $(this).prop("checked", true);
                    $(this).prop("disabled", true);
                    $(this).addClass("by_"+$current_id);
                    $('#hidden_'+$(this).val()).prop("disabled", false);
                }
            })
        }
        else {
            $current_id = $(this).val();
            $('.option_chb input:checkbox').each(function(){
                if ($.inArray($(this).val(), $cur_options_arr) !== -1){
                    $(this).removeClass("by_"+$current_id);
                    $className = $(this).attr('class').split(' ');
                    if ($className.length == 1) {
                        $(this).prop("disabled", false);
                        $('#hidden_'+$(this).val()).prop("disabled", true);
                    }
                }
            })
        }
    }).change(function () {
        var $req_options = $(this).data("req");
        var $options_arr = $req_options.split(' ');
        if (this.checked){
            $current_id = $(this).val();
            $('.option_chb input:checkbox').each(function(){
                if ($.inArray($(this).val(), $options_arr) !== -1){
                    $(this).prop("checked", true);
                    $(this).prop("disabled", true);
                    $(this).addClass("by_"+$current_id);
                    $('#hidden_'+$(this).val()).prop("disabled", false);
                }
            })
        } else {
            $current_id = $(this).val();
            $('.option_chb input:checkbox').each(function(){
                if ($.inArray($(this).val(), $options_arr) !== -1){
                    $(this).removeClass("by_"+$current_id);
                    $className = $(this).attr('class').split(' ');
                    if ($className.length == 1) {
                        $(this).prop("disabled", false);
                        $('#hidden_'+$(this).val()).prop("disabled", true);
                    }
                }
            })
        }
    });
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