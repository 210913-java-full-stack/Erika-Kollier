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


//Need to pass in token?

// function ticketPurchase() {
//   let userFrom = document.getElementById("currentCity").value;
//   let userTo = document.getElementById("destCity").value;
//   let userTicket = document.getElementById("tickets").value;
//   let userDeparture = document.getElementById("departure").value;
//   let userArrival = document.getElementById("arrival").value;

//   let userTicketData = {
//     tickets: {
//       currentCity: userFrom,
//       destCity: userTo,
//       tickets: userTicket,
//       departure: userDeparture,
//       arrival: userArrival,
//     },
//   };

//   console.log(
//     `ticketInfo => ${userTicketData.tickets.currentCity} ${userTicketData.tickets.destCity} ${userTicketData.tickets.tickets} ${userTicketData.tickets.departure} ${userTicketData.tickets.arrival}`
//   );
//   let url = "http://localhost:8080/Erika-Kollier/ticketPurchase";

//   let response = fetch(url, {
//     method: "POST",
//     headers: {
//       "Content-Type": "application/json;charset=utf-8",
//     },
//     body: JSON.stringify(userTicketData),
//   })
//     .then((response) => response.json())
//     .then(function (reponse) {
//       console.log(reponse.json());
//     })
//     .catch((error) => {
//       console.log(error);
//     });
// }
