


/**
 * Logout Function
 */
function userLogout() {
    localStorage.clear();
    console.log("clicked");
  }
  
/**  Add more logic? and create a logout button add eventlistener
    When mapping through tickets for passenger add a cancel ticket button and a check in button
    getTrainList function fetchs the list of all the train routes
    Use a for loop ti essentially may through the array and populate the table
    */

/**
 * Get method to receive list of train schedule & passengers
 */
(async function getTrainList() {
  let trainUrl = 'http://localhost:8080/Erika-Kollier/train';

  let response = await fetch(trainUrl, {
    method: "GET",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    }
  })

  let json = await response.json();
  await populateTable(json);
})();

function populateTable(json) {
  let table = document.getElementById("TrainRouteTable");
  let rows = Object.values(json).pop().length;
  let values = Object.values(json).pop();
  console.log(rows);
  console.log(values);

  try {
    for (let i = 0; i < rows; i++) {
      let tr = table.insertRow(0);
      for (let value of values.pop()) {
        let cell = tr.insertCell(-1);
        cell.innerHTML = value;
      }
    }
  } catch (e) {
    console.log(e);
  }
}