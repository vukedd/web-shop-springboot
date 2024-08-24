let star1 = document.querySelector("#star1");
let star2 = document.querySelector("#star2");
let star3 = document.querySelector("#star3");
let star4 = document.querySelector("#star4");
let star5 = document.querySelector("#star5");
let ratingInput = document.querySelector("#ratingInput");

star1.addEventListener("click", function () {
	console.log(1);
	star1.className = "bi bi-star-fill";
	star2.className = "bi bi-star";
	star3.className = "bi bi-star";
	star4.className = "bi bi-star";
	star5.className = "bi bi-star";
	ratingInput.value = 1;
});

star2.addEventListener("click", function () {
	console.log(2);
	star1.className = "bi bi-star-fill";
	star2.className = "bi bi-star-fill";
	star3.className = "bi bi-star";
	star4.className = "bi bi-star";
	star5.className = "bi bi-star";
	ratingInput.value = 2;
});

star3.addEventListener("click", function () {
	console.log(3);
	star1.className = "bi bi-star-fill";
	star2.className = "bi bi-star-fill";
	star3.className = "bi bi-star-fill";
	star4.className = "bi bi-star";
	star5.className = "bi bi-star";
	ratingInput.value = 3;
});

star4.addEventListener("click", function () {
	console.log(4);
	star1.className = "bi bi-star-fill";
	star2.className = "bi bi-star-fill";
	star3.className = "bi bi-star-fill";
	star4.className = "bi bi-star-fill";
	star5.className = "bi bi-star";
	ratingInput.value = 4;
});

star5.addEventListener("click", function () {
	console.log(5);
	star1.className = "bi bi-star-fill";
	star2.className = "bi bi-star-fill";
	star3.className = "bi bi-star-fill";
	star4.className = "bi bi-star-fill";
	star5.className = "bi bi-star-fill";
	ratingInput.value = 5;
});