
const token = localStorage.getItem("token") || ""; //jwt token
const defaultHeader = {
  "Content-Type": "application/json",
  Accept: "application/json",
  Authorization: "Bearer " + localStorage.getItem("token"),
};
async function fetchUser() {
  const users = await fetch(`${api}/api/users/me`, {
    headers: { ...defaultHeader },
  }).then((res) => res.json());
  return users;
}
async function logout() {
  localStorage.removeItem("token");
  window.location.href = "index.html";
}
function kiemtra(event) {
  if(token === ""){
    event.preventDefault();
    alert("Vui lòng đăng nhập để mua hàng");
    window.location.href = "signIn.html";
  }
}
if (token !== "") {
  async function load() {
    let user = await fetchUser();
    document.getElementById('totalitem1').innerText = user.userCartItems.length;
    document.getElementById("dangnhap1").innerText = " ";
    document.getElementById("username1").innerText = user.username;
    document.getElementById("username1").href = "#";
    document.getElementById("danhmuc1").innerHTML += `
    <li onclick="logout()" style="color: white;padding: -1px 15px" href="#" >Đăng Xuất</li>`;
    document.getElementById('totalitem2').innerText = user.userCartItems.length;
    document.getElementById("dangnhap2").innerText = " ";
    document.getElementById("username2").innerText = user.username;
    document.getElementById("username2").href = "#";
    document.getElementById("danhmuc2").innerHTML += `
    <li onclick="logout()" href="#">Đăng Xuất</li>`;
  }
  load();
}
async function search(event) {
  event.preventDefault();
  localStorage.setItem('query', document.getElementById("search-input").value.trim());
  window.location.href = "search.html";
}