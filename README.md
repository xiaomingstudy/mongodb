'use strict';

var pkg = require('../../package.json');
var logger = require('../../common/logger');
var cp = require('child_process');
var os = require('os');
var Promise = require('bluebird')
var adb = require('adbkit')
var client = adb.createClient()

var arrDeviceList = [];

var iosDevices=[];
var strText, match;
var platform=os.platform();




strText = cp.execSync('idevice_id -l').toString();
var arr = strText.toString('ascii').split('\n').map(function (val) {  
  return String(val);  
});  
for(var i=0;i<arr.length;i++){
    if(arr[i]!=''){
       var devices = cp.execSync('ideviceinfo -u '+arr[i]+'').toString();
       var devicesArray = devices.toString('ascii').split('\n').filter(function (val) {  
            return val.indexOf('UniqueDeviceID')==0||
                   val.indexOf('DeviceClass')==0||
                   val.indexOf('ProductVersion')==0||
                   val.indexOf('DeviceName')==0;  
        });
        iosDevices.push(devicesArray);
    } 
}  
var list =[];
var specificData=[];
for(var i=0;i<iosDevices.length;i++){
    var ss = iosDevices[i];
    var screen='';
    for(var j=0;j<ss.length;j++){
        var devicesArray = ss[j].toString('ascii').split(',');
        var sss = devicesArray.toString('ascii').split(':');
        list.push(sss[1]);
    }
    specificData.push(list);
    list=[];
}

var screen = '';
for(var i=0;i<specificData.length;i++){
        var deviceSpecificData=specificData[i];
        // var deviceName = deviceSpecificData[1].toString().replace(/\s/g, "");
        // deviceName = deviceName.toLowerCase();
        // console.log(deviceName);
        // if(deviceName=='iphone5s'||deviceName=='iphone5c'||deviceName=='iphone5'){
        //     screen = '640x1136';
        // }else if(deviceName=='iphone6'||deviceName=='iphone6s'){
        //     screen = '750x1334';
        // }else if(deviceName=='iphone6plus'||deviceName=='iphone6splus'){
        //     screen = '1080x1920';
        // }else if(deviceName=='iphone4'||deviceName=='iphone4s'){
        //     screen = '640x960';
        // } 
    arrDeviceList.push({
     serialNumber:deviceSpecificData[3].trim(),
     model:'iPhone 6s',
     brand:deviceSpecificData[0].trim(),
     releaseVersion:deviceSpecificData[2].trim(),
     plantForm:'ios',
     screen:'750x1334',
     status:'1'
    });
}
console.log(arrDeviceList);
client.exit;

