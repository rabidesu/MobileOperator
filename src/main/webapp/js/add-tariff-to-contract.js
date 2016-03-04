$(document).ready(function f() {
    $('.option_chb input:checkbox').on('click', function() {
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
});
