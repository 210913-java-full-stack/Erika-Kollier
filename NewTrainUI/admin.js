
/**
 * @author Erika Johnson & Kollier Martin
 * @description userLogout function logs out current user by
 * clearing the token from the local Storage.
 */
 function userLogout() {
  localStorage.clear();
  console.log("Logged Out");
  window.location.href = "index.html";
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
  })

function populateTable(json) {
  let table = document.getElementById("TrainRouteTable");
  let rows = Object.values(json).pop().length;
  let values = Object.values(json).pop();

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

// function createTrip() {
//   console.log(document.getElementById("theState").value);
//   let theState = document.getElementById("theState").value;

//   let theCity = document.getElementById("theCity").value;

//   let stationName = document.getElementById("stationName").value;

//   let departureDate = document.getElementById("departureDate").value;

//   let arrivalDate = document.getElementById("arrivalDate").value;

//   let tripInfo = {
//       theState: theState,
//       theCity: theCity, 
//       stationName: stationName, 
//       departureDate: departureDate, 
//       arrivalDate: arrivalDate
//     }
  
//   console.log(`tripInfo => ${tripInfo.theState} ${tripInfo.theCity} ${tripInfo.stationName} ${tripInfo.departureDate} 
//   ${tripInfo.arrivalDate}`)

//   let tripURL = 'http://localhost:8080/Erika-Kollier/train';

//   // Sends using fetch
//   fetch(tripURL, 
//     {method: "POST",
//     headers: { "Content-Type": "application/json; charset=utf-8"},
//     body: JSON.stringify(tripInfo)
//   })
//   .then((response) => response.json())
//   .then(function (response){
//   console.log(response.json());
//   })
//   .catch((error) => {
//     console.log(error);
//   })
// }
