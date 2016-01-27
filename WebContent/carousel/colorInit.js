/**
 * 
 */

var color0 = [
    ['#556B2F', '0%'], 
    ['#C0FF3E', '14%'], 
    ['#BCEE68', '29%'], 
    ['#9ACD32', '43%'], 
    ['#A2CD5A', '57%'], 
    ['#9BCD9B', '72%'], 
    ['#8FBC8F', '86%'], 
    ['#C0FF3E', '100%']
];

var color1 = [
    ['#0f9d58', '0%'], 
    ['#0f9d58', '25%'], 
    ['#f4b400', '25%'], 
    ['#f4b400', '50%'], 
    ['#db4437', '50%'], 
    ['#db4437', '75%'], 
    ['#4285f4', '75%'], 
    ['#4285f4', '100%']
];
document.addEventListener('DOMContentLoaded', function() { 
   
    var bar1 = new colorBar('.bar1', {
        id: 'bar1',
        height: '5px',
        colors: color1
    })

    function click(tag, callback) {
        document.getElementById(tag).addEventListener('click', function() {
            callback()
        }, false)
    }

   
})
