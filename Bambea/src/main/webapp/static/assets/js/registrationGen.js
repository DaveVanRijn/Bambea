$(document).on('submit', '#regLinkGenForm',
        function (event) {


            var token = $('#csrfToken').val();
            var header = $('#csrfHeader').val();
            var regLink = $('#regLink').val();
            var nuOfRegistrations = $('#nuOfRegistrations').val();
            var userRole = $('#userrol :selected').val();
            console.log(userRole);

            
            
            var json = {"regLink": regLink, "nuOfRegistrations": nuOfRegistrations, "userRole": userRole};

            $.ajax({
                url: $('#regLinkGenForm').attr("action"),
                contentType: 'application/json',
                data: JSON.stringify(json),
                type: "POST",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                    xhr.setRequestHeader("Accept", "text/plain");
                },
                success: function (el) {
                    document.getElementById("linkResult").value = el;
                },
                error: function (el) {
                    console.log(el);
                }
            })
            event.preventDefault();
        });
        
function copy() {
    var linkResult = document.getElementById("linkResult");
    linkResult.select();
    var successful = document.execCommand('copy');
}