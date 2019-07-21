
var logOut = function () {
    var form = document.createElement("form");
    form.method = "POST";
    form.action = "adminLogOutServlet";
    document.body.appendChild(form);
    form.submit();
}