function logout(e) {
    e.preventDefault();
    e.stopImmediatePropagation();
    document.getElementById('logoutForm').submit()
}

document.addEventListener("DOMContentLoaded", function () {
    const logoutBtnSidebar = document.getElementById('logoutBtnSidebar');
    const logoutBtnNavbar = document.getElementById('logoutBtnNavbar');
    logoutBtnSidebar.addEventListener('click', logout);
    logoutBtnNavbar.addEventListener('click', logout);
});