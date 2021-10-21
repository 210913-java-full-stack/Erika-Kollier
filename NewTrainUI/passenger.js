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