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
    Use a for loop to essentially may through the array and populate the table
    */

  /**
   * Ticket Purchase will use onClick listener to call ticketPurchase on button when form is submitted
   */

   function ticketPurchase() {

    let url = "http://localhost:8080/Erika-Kollier/ticketPurchase";
    const form = document.getElementById("purchaseTicketForm");


  //   //button type has to be submit or call onclick event directly on button
    form.addEventListener("submit", function(e) {
      e.preventDefault();
      console.log(ticket.value, currentCity.value, destCity.value, departure.value, arrival.value);
      submitForm(tickets.value, currentCity.value, destCity.value, departure.value, arrival.value);
    })

    async function submitForm(ticketValue, currentCityValue, destCityValue, departureValue, arrivalValue) {
      let formObject = {
        ticket: ticketValue,
        currentCity: currentCityValue, 
        destCity: destCityValue,
        departure: departureValue, 
        arrival: arrivalValue
      }

      console.log(formObject);

      let response = fetch(url, {
        method: 'POST',
        headers:{
             "Content-Type": "application/json;charset=utf-8",
            },
        body: JSON.stringify(formObject)
      })
      let result = await response.json();
      console.log(result);
    }
  }

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

  