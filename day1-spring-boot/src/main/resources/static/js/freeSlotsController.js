	var request = new XMLHttpRequest()
	request.open('GET', '/sample', true)

	request.onload = function () {
		// Begin accessing JSON data here
		//console.log(this.response)
		var myObj, x, txt = ""
		myObj= JSON.parse(this.response);
		txt += "<select>"
		for (x in myObj) {
		txt += "<option>" + myObj[x].start + ":" + myObj[x].end +"</option>";
		}
		txt += "</select>"
		console.log(txt)
		document.getElementById("listofslots").innerHTML = txt;
		}

	request.send()
