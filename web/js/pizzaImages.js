window.addEventListener( 'load', init );

function init() {

    var checkBox = document.querySelectorAll('input');
    
    for (i = 0; i < checkBox.length; i++){
        checkBox[i].addEventListener( 'change', toppingShow );
    }
}

function toppingShow (ev) {

    // get reference to the target which generated the event
    var target = ev.target;

    // type is the variable of the element that will be processed
    var topping = ev.target.value;

    var toppingImage = document.querySelector('#' + topping);

    // Show topping
    if (target.checked == true){
        toppingImage.style.display = "block";
    }   
    // Remove topping
    else {
        toppingImage.style.display = "none";
    }
}