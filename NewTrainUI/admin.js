
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

function createATrain() {

 const form = document.getElementById("createTrain");
 const tbody = document.getElementById("TrainRouteTable")
 const trainTable = document.getElementById("trainSchedule")

 function submitForm(e) {
   e.preventDefault();
   let trainID = document.getElementById("trainID").value; //departureCity
   let totalPassengers = document.getElementById("totalPassengers").value;
   let departureStation = document.getElementById("departureStation").value;
   let arrivalStation = document.getElementById("arrivalStation").value;
   let departureDate = document.getElementById("departureDate").value
   let arrivalDate = document.getElementById("arrivalDate").value;

     tbody.innerHTML += `
        <tr>
              <td>${trainID}</td>
              <td>${0}</td>
              <td>${arrivalStation}</td>
              <td>${arrivalDate}</td>
              <td>${departureStation}</td>
              <td>${departureDate}</td>
              <td>true</td>
            
              <td><button type = "button" class = "deleteButton" style="background-color:#0d6efd; color:white;position: static;">Cancel</button></td>
        </tr> 
   `

     let buttons = document.getElementsByClassName('Imma Button');

     for (let i = 0; i < buttons.length; i++) {
         let button = buttons[i];
         let tr = tbody.getElementsByTagName('tr');
         let trData = tr.item(i);
         trData.appendChild(button);
     }
 }

 function deleteRow(e){
  if(!e.target.classList.contains("deleteButton")) {
    return;
  }
  alert("Train Route Canceled")
  const btn = e.target;
  btn.closest("tr").remove();
}
   form.addEventListener("submit", submitForm)
   trainTable.addEventListener("click", deleteRow) 
 
}
//  form.addEventListener("submit", function(e) {
//    e.preventDefault();
//    submitForm(theState.value, theCity.value, stationName.value, departureDate.value, arrivalDate.value);
//    console.log(submitForm)
//  })
 
//  async function submitForm(theStateValue, theCityValue, stationNameValue, departureDateValue, arrivalDateValue) {
//    let tripInfo = {
//      theState: theStateValue,
//      theCity: theCityValue, 
//      stationName: stationNameValue, 
//      departureDate: departureDateValue, 
//      arrivalDate: arrivalDateValue
//    }
 
//    console.log("tripInfo: ", tripInfo)
 
//    let tripURL = 'http://localhost:8080/Erika-Kollier/train';
 
//    let response = await fetch(tripURL, {
//      method: "POST",
//      headers: {
//        "Content-Type": "application/json;charset=utf-8",
//      },
//      body: JSON.stringify(tripInfo)
//    })
//    window.location.href ="adminView.html"
//  }


/** getTrainList function fetchs the list of all the train routes
    Use a for loop to essentially map through the array and populate the table
    */


/***LIST OF PASSENGER INFO AND TRAIN INFO, DISPLAYED WITHIN A TABLE */
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
        var btn = document.createElement('input');
        btn.type = "button";
        btn.value = "Cancel";
        btn.className = "Imma Button";
        btn.style = "background-color:#0d6efd; " +
            "color:white; " +
            "position: relative; " +
            "padding: 1px 6px; " +
            "align: center; " +
            "left: 8px; " +
            "display: inline-block; " +
            "text-align: center; " +
            "align-items: flex-start; " +
            "top: 5px; ";

      for (let value of values.pop()) {
        let cell = tr.insertCell(-1);
          cell.innerHTML = value;
          tr.append(btn);
      }
    }
  } catch (e) {
    console.log(e);
  }
}

/***CREATE A TICKET, VIEW TICKET FOR CHECKIN OR CANCEL */


function createATicket() {

const form = document.getElementById("purchaseTicketForm")
const tbody = document.getElementById("viewTickets")
const tableEl = document.getElementById("userTickets")


function onAddTickets(e) {
  e.preventDefault();
  // alert("this works")
  const currentCity = document.getElementById("currentCity").value;
  const destination = document.getElementById("destCity").value;
  const totalTickets = document.getElementById("tickets").value;
  const departDate = document.getElementById("departure").value;
  const arrivalDate= document.getElementById("arrival").value;
  // alert(currentCity + " " + destination + " " + totalTickets + " "+ departDate + " " +arrivalDate)
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
}
// only want to respond to the delete button associated with the row on the table. 
// closest finds the closest element (button)
//classList property is read-only, however, you can modify it by using the, toggle add() and remove() methods.
function onDeleteRow(e){
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