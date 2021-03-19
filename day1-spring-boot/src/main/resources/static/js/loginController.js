document.getElementById("submitLoginDetails").addEventListener("click", () => {

        submitLoginForm();

})

function submitLoginForm() {
   var url = window.location.origin;
    url = window.location.origin + "/user.html"

    window.location.assign(url)
}
