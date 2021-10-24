
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
  
    //can wrap in function and then call it using the onclick button?
    // <td>${trainId}</td>
   // <td>${totalPass}</td>
  

function createATrain() {

 const form = document.getElementById("createTrain")
 const tbody = document.getElementById("TrainRouteTable")
 const trainTable = document.getElementById("trainSchedule")

 function submitForm(e) {
   e.preventDefault();
   let departureCity = document.getElementById("departureCity").value;
   let arrivalCity = document.getElementById("arrivalCity").value;
   let departureStation = document.getElementById("departureStation").value;
   let arrivalStation = document.getElementById("arrivalStation").value;
   let departureDate = document.getElementById("departureDate").value
   let arrivalDate = document.getElementById("arrivalDate").value;
   const token = localStorage.getItem("Token");



   
   tbody.innerHTML += `
        <tr>

             
              <td>${departureCity}</td>
              <td>${arrivalCity}</td>
              <td>${arrivalDate}</td>
              <td>${departureStation}</td>
              <td>${arrivalStation}</td>
              <td>${departureDate}</td>
              
              <td><button type = "button" class = "btn-success">Cancel</button></td>
              
        </tr> 
    
   `

    let baseUrl = "http://localhost:8080/Erika-Kollier/createTrain";

   let newEntry = {train: {departureCity: departureCity, arrivalCity: arrivalCity, departureStation: departureStation, arrivalStation: arrivalStation, departureDate: departureDate, arrivalDate: arrivalDate}}
   console.log(newEntry)


   fetch(baseUrl, {
     method:"POST",
     headers: {
      "Content-Type": "application/json;charset=utf-8", 
      "Authorization": token
     },
     body: JSON.stringify(newEntry),
   })
   .then(response => response.json())
   .then(function (response) {
    console.log(response)
   })
   .catch((err) => {
     console.log(err);
   })
 }

 function deleteRow(e){

  const deleteUrl = "http://localhost:3000/Erika-Kollier/train"
  const token = localStorage.getItem("Token");

  fetch(deleteUrl, {
    method: 'DELETE',
    headers: {
      "Content-Type": "application/json;charset=utf-8", 
      "Authorization": token
    }
  }).then(response => {
    console.log(response)
  })

  if(!e.target.classList.contains("btn-success")) {
    return;
  }
  alert("Train Route Canceled")
  const btn = e.target;
  btn.closest("tr").remove();
}
   form.addEventListener("submit", submitForm)
   trainTable.addEventListener("click", deleteRow) 
 
}

//May have to pass in token for getting train routes and want to display just your scheduled routes
//may also need another get request to display your train routes when refreshing

/***LIST OF PASSENGER INFO AND TRAIN INFO, DISPLAYED WITHIN A TABLE */
 (async function getTrainList() {
  let trainUrl = 'http://localhost:8080/Erika-Kollier/train';
  const token = localStorage.getItem("Token");


  let response = await fetch(trainUrl, {
    method: "GET",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      "Authorization": token
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
  } catch (error) {
    console.log("Error:", error);
  }
}

/***CREATE A TICKET, VIEW TICKET FOR CHECKIN OR CANCEL */


function createATicket() {

const form = document.getElementById("purchaseTicketForm")
const tbody = document.getElementById("viewTickets")
const tableEl = document.getElementById("userTickets")


function onAddTickets(e) {
  e.preventDefault();
  const currentCity = document.getElementById("currentCity").value;
  const destination = document.getElementById("destCity").value;
  const totalTickets = document.getElementById("tickets").value;
  const departDate = document.getElementById("departure").value;
  const arrivalDate= document.getElementById("arrival").value;
  const token = localStorage.getItem("Token");

  alert("Ticket(s) Purchased")
  tbody.innerHTML += `
  <tr> 

        <td>${currentCity}</td>
        <td>${destination}</td>
        <td>${totalTickets}</td>
        <td>${departDate}</td>
        <td>${arrivalDate}</td>
        <td><button type = "button" class = "btn-warning">Cancel</button></td>
        <td><button type = "button" class = "btn-primary">CheckIn</button></td>
        
  </tr>
  
  `

  let baseUrl = "http://localhost:8080/Erika-Kollier/createTicket";

  let newTicket = {train: {currentCity: currentCity, destination: destination, totalTickets: totalTickets, 
  departDate: departDate, arrivalDate: arrivalDate}}
  console.log(newTicket)


  fetch(baseUrl, {
    method:"POST",
    headers: {
     "Content-Type": "application/json;charset=utf-8", 
     "Authorization": token
    },
    body: JSON.stringify(newTicket),
  })
  .then(response => response.json())
  .then(function (response) {
   console.log(response)
  })
  .catch((err) => {
    console.log(err);
  })
}
// only want to respond to the delete button associated with the row on the table. 
// closest finds the closest element (button)
//classList property is read-only, however, you can modify it by using the, toggle add() and remove() methods.
function onDeleteRow(e){

  const deleteUrl = "http://localhost:3000/Erika-Kollier/tickets"
  const token = localStorage.getItem("Token");

  fetch(deleteUrl, {
    method: 'DELETE',
    headers: {
      "Content-Type": "application/json;charset=utf-8", 
      "Authorization": token
    }
  }).then(response => {
    console.log(response)
  })

  if(!e.target.classList.contains("btn-warning")) {
    return;
  }
  alert("Your ticket will be canceled")
  const btn = e.target;
  btn.closest("tr").remove();
}

function onCheckinRow(e){
  if(!e.target.classList.contains("btn-primary")) {
    return;
  }
  alert("You have successfully checked in")
  const btn = e.target;
  btn.closest('tr').alert("checked in")

}

form.addEventListener('submit', onAddTickets)
//delete row from table
tableEl.addEventListener("click", onDeleteRow) 
tableEl.addEventListener("click", onCheckinRow)

}

(async function getTicketList() {
  let ticketUrl = 'http://localhost:8080/Erika-Kollier/myTickets';
  const token = localStorage.getItem("Token");


  let response = await fetch(ticketUrl, {
    method: "GET",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      "Authorization": token
    }
  })

    let json = await response.json();
    await populateTable(json);
  })();

function populateTable(json) {
  let table = document.getElementById("userTickets")
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
  } catch (error) {
    console.log("Error:", error);
  }
}
