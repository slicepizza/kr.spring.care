document.addEventListener("DOMContentLoaded", function() {
    var slides = document.querySelectorAll('.mySlides');
    var currentSlide = 0;

    // Function to go to the next slide
    function nextSlide() {
        slides[currentSlide].classList.remove('active');
        currentSlide = (currentSlide + 1) % slides.length;
        slides[currentSlide].classList.add('active');
    }

    // Set an interval to change slides
    setInterval(nextSlide, 5000); // Change slide every 4 seconds

    // Initialize the slideshow
    slides[currentSlide].classList.add('active');
});