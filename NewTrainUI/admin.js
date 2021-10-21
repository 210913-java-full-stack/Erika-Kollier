/**
 * Logout Function
 */
function userLogout() {
  localStorage.setItem("Token", Object.values(token).toString());
  localStorage.clear();
  console.log("clicked");
  window.location.href = "index.html";
}

/**  Add more logic? and create a logout button add eventlistener
    When mapping through tickets for passenger add a cancel ticket button and a check in button
    getTrainList function fetchs the list of all the train routes
    Use a for loop to essentially may through the array and populate the table
    */

/**
 * Get method to receive list of train schedule & passengers
 */
(async function getTrainList() {
  let response = await fetch("http://lcoalhost:8080/Erika-Kollier/trainList");
  let json = await response.json();

  let table = document.getElementById("TrainRouteTable");

  for (let element of json) {
    let tr = table.insertRow(-1);
    for (let key in element) {
      let cell = tr.insertCell(-1);
      cell.innerHTML = element[key];
    }
  }
})();
