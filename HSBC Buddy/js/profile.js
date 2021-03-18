
document.getElementById("Profile").addEventListener("click", () => {
    
    changeDisplay.changeDisplayType("Profile")
    
})

// neLearning" href="#">Machine Learning</a></li>
// 										<li><a  id="cricket" href="#">Cricket</a></li>
// 										<li ><a id="cyberSecurity" href="#">Cyber Security</a></li>
// 										<li><a  id="blockchain" href="#">BlockChain</a></li>
// 										<li ><a id="finan


arr=["blockchain"]
// var tracker = ["0"];
var contactParent = document.querySelector("#InterestGroupList");
contactParent.addEventListener("click", DisplayInterestGroupChat, false);

document.getElementById("joinGroup").addEventListener("click",()=>{
    groupName = document.querySelector(".groupName").innerHTML;
    if(groupName=="Cyber Security"){
        groupName = "cyberSecurity";
    }else if(groupName=="Cricket"){
        groupName="cricket"
    }else if(groupName=="Machine Learning"){
        groupName="machine Learning"
    }else if(groupName=="Finance"){
        groupName - "finance"
    }else if(groupName=="BlockChain"){
        groupName="blockchain";
        
    }
    arr.push(groupName);
    document.getElementById("joinGroup").disabled =true;
    document.getElementById("inputText").disabled = false;
    localStorage.setItem("myrooms",arr)
    console.log(arr);
})

document.getElementById("leaveGroup").addEventListener("click",()=>{
    groupName = document.querySelector(".groupName").innerHTML;
    if(groupName=="Cyber Security"){
        groupName = "cyberSecurity";
    }else if(groupName=="Cricket"){
        groupName="cricket"
    }else if(groupName=="Machine Learning"){
        groupName="machine Learning"
    }else if(groupName=="Finance"){
        groupName - "finance"
    }else if(groupName=="BlockChain"){
        groupName="blockchain";
        
    }
    arr.pop(groupName);
    document.getElementById("joinGroup").disabled =false;
    document.getElementById("inputText").disabled = true;
    localStorage.setItem("myrooms",arr)
    console.log(arr);
})

function DisplayInterestGroupChat(e) {

    if (e.target !== e.currentTarget) {


        strc = e.target.id;
        console.log(strc)
        var element = document.getElementById(strc);
        // tracker.push(strc);
        document.querySelector(".InterestGroupChat").style.display = "block";
        document.querySelector(".groupName").innerHTML = element.innerText;
        localStorage.setItem("myrooms",arr)


        console.log(element.innerText);
        if(localStorage.getItem("myrooms").includes(strc)){
            console.log("hi")
            document.getElementById("joinGroup").disabled =true;
            document.getElementById("inputText").disabled = false;
        }else{
            document.getElementById("joinGroup").disabled =false;
            document.getElementById("inputText").disabled = true;
        }
       
    }
}

document.getElementById("joinMeeting").addEventListener("click",()=>{

    http = new XMLHttpRequest();
    groupName = document.querySelector(".groupName").innerHTML;
    // getMeetingDetails(groupName);
    getLink(groupName)
    
   

})

function getMeetingDetails(groupName){
    console.log(groupName);
    http.onreadystatechange = generateLink;
    http.open("GET", "list_meeting", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("topic=" + groupName);
}

meetings_list={"page_size":30,"page_number":null,"page_count":null,"total_records":2,"next_page_token":"","meetings":[{"id":94306231570,"uuid":"N6Sn8MsxTfaUB0G4A+CVCg==","assistant_id":null,"host_email":null,"registration_url":null,"topic":"HSBC Buddy - Talk Room 2","type":2,"start_time":"2021-03-18T08:26:26Z","duration":60,"schedule_for":null,"timezone":"Asia/Calcutta","created_at":"2021-03-18T08:26:25Z","password":null,"agenda":null,"start_url":null,"join_url":"https://zoom.us/j/94306231570?pwd=bFp1RVRsS3dOUmsrRDRYOFpmU0JLQT09","h323_password":null,"pmi":null,"recurrence":null,"tracking_fields":null,"occurrences":null,"settings":null},{"id":96763160972,"uuid":"Y+Qxnl9TRraKeOmZ1v3scA==","assistant_id":null,"host_email":null,"registration_url":null,"topic":"HSBC Buddy - Talk Room 1","type":2,"start_time":"2021-03-18T08:45:29Z","duration":60,"schedule_for":null,"timezone":"Asia/Calcutta","created_at":"2021-03-18T08:45:29Z","password":null,"agenda":null,"start_url":null,"join_url":"https://zoom.us/j/96763160972?pwd=eUsrYnhNeHl3Yk93TzVoTGRNMWdrQT09","h323_password":null,"pmi":null,"recurrence":null,"tracking_fields":null,"occurrences":null,"settings":null}]}

function  getLink(groupName){

    var meeting= meetings_list.meetings.filter(i=>i.topic==='HSBC Buddy - Talk Room 2');
    console.log(meeting)
    var link = meeting[0]['join_url'];
    var a = document.getElementById("linkMeeting");
    a.href=link;
    console.log(link)
    
}

function generateLink(){
    if (http.readyState == 4) {
        text = "";
        if (http.status == 200) {
            res = http.responseText;
            obj = JSON.parse(res)
            console.log(obj)


            if ("message" in obj) {
                console.log(obj.message);
                return;
            } else {


                $("#autoPopulateContact").modal('show');
                document.getElementsByTagName("body")[0].removeAttribute("style");
                document.getElementsByTagName("body")[0].removeAttribute("class");
            }
        }
        
    }
}


