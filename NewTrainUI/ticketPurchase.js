function ticketPurchase() {

  const form = document.getElementById("purchaseTicketForm");

  //button type has to be submit or call onclick event directly on button
  form.addEventListener("button", function (e) {
    e.preventDefault();
    submitForm(
      tickets.value,
      currentCity.value,
      destCity.value,
      departure.value,
      arrival.value
    );
  });

  async function submitForm(
    ticketValue,
    currentCityValue,
    destCityValue,
    departureValue,
    arrivalValue
  ) {
    let ticketPurchase = {
      tickets: ticketValue,
      currentCity: currentCityValue,
      destCity: destCityValue,
      departure: departureValue,
      arrival: arrivalValue,
    };

    console.log("TicketInfo: ", ticketPurchase);
    
    let url = "http://localhost:8080/Erika-Kollier/ticketPurchase";

    let response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=utf-8",
      },
      body: JSON.stringify(ticketPurchase),
    });
  }
  window.location.href = "adminView.html";
}

//Need to pass in Token maybe?
/******Display user tickets*******/

(async function getMyTicketList() {
  let displayTicket = 'http://localhost:8080/Erika-Kollier/ticketDisplay'

  let response = await fetch(displayTicket, {
    method: "GET",
    headers: {
      "Content-Type": "application/json:charset=utf-8,"
    }
  })

  let json = await response.json()
  await populateTicketTable(json)
})();

function populateTicketTable(json) {
  let table = document.getElementById("ticketDisplay")
  let rows = Object.values(json).pop().length;
  let values = Object.values(json).pop();

  for(let i = 0; i < rows; i++){
    let tr = table.insertRow(0)
    for(let value of values.pop()) {
      let cell = tr.insertCell(-1)
      cell.innerHTML = value;
    }
  }
}


