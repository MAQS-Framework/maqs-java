let buttons = document.getElementsByClassName("sectionbutton");
let i;

for (i = 0; i < buttons.length; i++) {
  buttons[i].addEventListener("click", function () {
    let expandoText = this.getElementsByClassName("buttonExpandoText")[0];

    this.classList.toggle("active");

    let content = this.nextElementSibling;
    if (expandoText.innerHTML === "-") {
      content.style.maxHeight = 0;
      expandoText.innerHTML = "+";
    } else {
      content.style.maxHeight = content.scrollHeight + "px";
      expandoText.innerHTML = "-";
    }
  });
}

let thumbnail = document.getElementById("screenshotThumbnail");
let thumbnailStyle = getComputedStyle(thumbnail);
let modal = document.getElementById("modal");
let modalimg = modal.getElementsByTagName("img")[0];

modal.addEventListener("click", function () {
  modal.style.display = "none";
  modalimg.style.content = "";
  modalimg.alt = "";
});

thumbnail.addEventListener("click", function () {
  modal.style.display = "flex";
  modalimg.style.content = thumbnailStyle.content;
  modalimg.alt = thumbnail.alt;
});