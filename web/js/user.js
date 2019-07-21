
var submitSignUp = function () {
    var ready = true;
    var username = document.getElementById("txtUsername").value;
    var password = document.getElementById("txtPassword").value;
    var repassword = document.getElementById("txtRePassword").value;
    var name = document.getElementById("txtName").value;
    document.getElementById("usernameError").innerHTML = "";
    document.getElementById("passwordError").innerHTML = "";
    document.getElementById("mainError").innerHTML = "";
    document.getElementById("rePasswordError").innerHTML = "";

    if (name.length > 150) {
        document.getElementById("nameError").innerHTML = "name can only have at most 150 characters";
            ready = false;
    }
    if (username.length > 25) {
        document.getElementById("usernameError").innerHTML = "username can only have at most 25 characters";
        ready = false;
    }
    if (password.length > 25) {
        document.getElementById("passwordError").innerHTML = "username can only have at most 25 characters";
        ready = false;
    }
    if (password.length === 0 || username.length === 0 || name.length === 0) {
        document.getElementById("mainError").innerHTML = "all filed is required";
        ready = false;
    }

    if (password.length !== 0 && password !== repassword) {
        document.getElementById("rePasswordError").innerHTML = "Invalid Repassword";
        ready = false;
    }
   
    if (ready) {
        
        var btn = document.createElement("input");
        btn.name = "btnUser";
        btn.value = "submitSignUp";
        var form = document.getElementById("signUpForm");
        form.appendChild(btn);
         debugger;
        form.submit();
    }
}