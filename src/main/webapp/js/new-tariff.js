$(document).ready(function f() {
    $('.option_chb input:checkbox').on('click', function() {
        var $req_options = $(this).data("req");
        var $options_arr = $req_options.split(' ');

        var $current_id = $(this).val();
        if (this.checked){
            $('.option_chb input:checkbox').each(function(){
                if ($.inArray($(this).val(), $options_arr) !== -1){
                    $(this).trigger('click');
                    $(this).prop("checked", true);
                    $(this).prop("disabled", true);
                    $(this).addClass("by_"+$current_id);
                    $('#hidden_'+$(this).val()).prop("disabled", false);
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

            })
        }
    })
});
