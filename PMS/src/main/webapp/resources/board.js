function submitPage(pageNumber) {
  document.getElementById("page-input").value = pageNumber;
  document.getElementById("page-form").submit();
}


function search() {
  var keyword = document.getElementById("kewordInput").value;
  window.location.href = "board?keyword=" + keyword;
}