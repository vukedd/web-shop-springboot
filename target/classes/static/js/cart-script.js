document.addEventListener("DOMContentLoaded", function() {
    const minusSign = document.getElementById("cartItemMinus");
    const plusSign = document.getElementById("cartItemPlus");
    const quantity = document.getElementById("cartItemQuantity");
    const cartItemPrice = document.getElementById("cartItemPrice");

    if (minusSign) {
        minusSign.addEventListener("click", function (){
            if (eval(quantity.innerHTML) > 1){
                cartItemPrice.innerHTML = ((eval(cartItemPrice.innerHTML)) - (eval(cartItemPrice.innerHTML) / eval(quantity.innerHTML)));
                quantity.innerHTML = (eval(quantity.innerHTML)) - 1;
            } else3
                console.log("no");
        });
    }

    if (plusSign) {
        plusSign.addEventListener("click", function (){
            cartItemPrice.innerHTML = ((eval(cartItemPrice.innerHTML)) + (eval(cartItemPrice.innerHTML) / eval(quantity.innerHTML)));
            quantity.innerHTML = (eval(quantity.innerHTML)) + 1;
        });
    }
});