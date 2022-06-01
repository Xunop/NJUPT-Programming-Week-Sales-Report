let a = document.getElementsByClassName("page-link")
let b = 1
const submitButton = document.getElementById("submitButton")
function aaaa(a) {
    submitButton.addEventListener("click",()=>{
        const zoneInput = document.getElementById("zoneInput");
    })
    for (let c = 0; c < a.length; c++) {
        a[c].addEventListener("click", (e) => {
            b = e.target.lastChild.nodeValue
            console.log(b)
        })
    }
}
function generateReport() {
    var params = {};
    var temp = $('#transaction_statistics_market').combobox('getValue').trim();
    if(temp != -1){
        params.marketId = temp;
    }
    params.type = $('#transaction_stats_type').combobox('getValue').trim();
    params.startTime = $('#transaction-stats-datefrom').datebox('getValue').trim();
    params.endTime = $('#transaction-stats-dateto').datebox('getValue').trim();
    $.ajax({
        'url': 'search/?' + $.param(params),//发送参数
        method: "get",
        dataType: "json",
        success: function (res) {
            if (res.data != null) {
                window.location = "/CSV/" + res.data;
            }
        }
    });
}

function addLoadEvent(func) {
    let oldonload = window.onload;
    if (typeof window.onload != 'function') {
        window.onload = func;
    } else {
        window.onload = function () {
            oldonload();
            func();
        }
    }
}
addLoadEvent(aaaa(a));




