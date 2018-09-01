/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * displays notification...
 * @param {type} from
 * @param {type} align
 * @param {type} message
 * @param {type} type
 * @returns {undefined}
 */
function displaynotification(from, align, message, type) {
    $.notify({
        icon: "notifications",
        message: message

    }, {
        type: type,
        timer: 4000,
        placement: {
            from: from,
            align: align
        }
    });
}

/***
 * create user profile input form validation
 * @returns {Boolean}
 */
function validatecreateuser() {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var regex = /^[0-9]+$/;
    var emailID = document.getElementById("emailID").value;
    if (reg.test(emailID) === false) {
        displaynotification('top', 'right', 'Invalid EmailID Please Enter correct EmailID', 'danger');
        return false;
    }
    var phone = document.getElementById("phone").value;
    if (!phone.match(regex)) {
        displaynotification('top', 'right', 'Please Enter a valid phone number', 'danger');
        return false;
    }
    if (phone.length < 10) {
        displaynotification('top', 'right', 'Please Enter a valid phone number', 'danger');
        return false;
    }
    var firstname = document.getElementById("firstname").value;
    var password = document.getElementById("password").value;
    var cpassword = document.getElementById("cpassword").value;
    if (firstname === null | firstname === "" | firstname === " ") {
        displaynotification('top', 'right', 'First Name cannot be empty', 'danger');
        return false;
    }
    if (password === null | password === "" | password === " ") {
        displaynotification('top', 'right', 'Password cannot be empty', 'danger');
        return false;
    }
    if (password !== cpassword) {
        displaynotification('top', 'right', 'Passwords not matching', 'danger');
        return false;
    }
    var image = document.getElementById("image").value;
    if (image === null | image === "" | image === " ") {
        displaynotification("bottom", "right", "Image is not provided default image will be used..", "info");
    }
    return true;
}

/**
 * displays popup accordong to message type
 * @param {type} message
 * @param {type} type
 * @returns {undefined}
 */
function showpopup(message, type) {    
    if (message !== null && message !== "" && message !== " " && message !== "null") {
        if (type.toLowerCase() === "error") {
            displaynotification("top", "right", message, "danger");
        } else if (type.toLowerCase() === "info") {
            displaynotification("bottom", "right", message, "info");
        } else {
            displaynotification("bottom", "right", message, "success");
        }
    }
}