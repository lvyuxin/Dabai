package com.suomate.dabaiserver.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/6/21.
 */

public class ReadJson {

    /**
     * alldevice : {"type":"data","version":"D20170624V1.86","time":"2018/6/21 21:58:10","ipinfor":"mac=12:07:22:00:12:20,ip=192.168.1.231,mask=255.255.255.0,route=192.168.1.1,dns=192.168.1.1,udhcpc=off","data":[{"nid":231,"lid":0,"id":7,"portnum":3,"serial":"ET-D0201A       ","version":"D20150403V1.03  ","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"10000000"},{"port":2,"val":"10000001"}]},{"nid":231,"lid":0,"id":10,"portnum":1,"serial":"MI0606A","version":"V1.0.0","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"}]},{"nid":231,"lid":0,"id":11,"portnum":9,"serial":"ET-R0816A       ","version":"D20150804V1.11  ","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"00000000"},{"port":2,"val":"00000001"},{"port":3,"val":"00000000"},{"port":4,"val":"00000000"},{"port":5,"val":"00000000"},{"port":6,"val":"00000000"},{"port":7,"val":"00000000"},{"port":8,"val":"00000000"}]},{"nid":231,"lid":0,"id":28,"portnum":5,"serial":"ET-R0416A       ","version":"D20150804V1.10  ","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"00000000"},{"port":2,"val":"00000000"},{"port":3,"val":"00000000"},{"port":4,"val":"00000000"}]},{"nid":231,"lid":0,"id":38,"portnum":9,"serial":"ET-DMX08A       ","version":"D20140303V1.00  ","mac8":"00:00:00:00:00:00:00:00","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"10000000"},{"port":2,"val":"10000000"},{"port":3,"val":"10000000"},{"port":4,"val":"10000000"},{"port":5,"val":"10000000"},{"port":6,"val":"10000000"},{"port":7,"val":"10000000"},{"port":8,"val":"10000000"}]},{"nid":231,"lid":0,"id":44,"portnum":17,"serial":"SMT-CLNET       ","version":"D20170214V1.00  ","mac8":"00:00:00:00:00:00:00:00","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"00000000"},{"port":2,"val":"00000000"},{"port":3,"val":"00000000"},{"port":4,"val":"00000000"},{"port":5,"val":"00000000"},{"port":6,"val":"00000000"},{"port":7,"val":"00000000"},{"port":8,"val":"0000000d"},{"port":9,"val":"00000000"},{"port":10,"val":"00000000"},{"port":11,"val":"00000000"},{"port":12,"val":"00000000"},{"port":13,"val":"00000000"},{"port":14,"val":"00000000"},{"port":15,"val":"00000000"},{"port":16,"val":"00000000"}]},{"nid":231,"lid":0,"id":52,"portnum":17,"serial":"KPS128A","version":"V1.0.2","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"ff000001"},{"port":2,"val":"ff000000"},{"port":3,"val":"ff000000"},{"port":4,"val":"ff000000"},{"port":5,"val":"ff000000"},{"port":6,"val":"ff000000"},{"port":7,"val":"ff000000"},{"port":8,"val":"ff000000"},{"port":9,"val":"ff000000"},{"port":10,"val":"ff000000"},{"port":11,"val":"ff000000"},{"port":12,"val":"ff000000"},{"port":13,"val":"ff000000"},{"port":14,"val":"ff000000"},{"port":15,"val":"ff000000"},{"port":16,"val":"ff000000"}]},{"nid":231,"lid":0,"id":53,"portnum":17,"serial":"SMT-XFZH        ","version":"D20170214V1.00  ","mac8":"00:00:00:00:00:00:00:00","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"00000000"},{"port":2,"val":"00000001"},{"port":3,"val":"00000003"},{"port":4,"val":"00000003"},{"port":5,"val":"0000002d"},{"port":6,"val":"00000014"},{"port":7,"val":"00000080"},{"port":8,"val":"00000000"},{"port":9,"val":"00000000"},{"port":10,"val":"00000000"},{"port":11,"val":"00000000"},{"port":12,"val":"00000000"},{"port":13,"val":"00000000"},{"port":14,"val":"00000000"},{"port":15,"val":"00000000"},{"port":16,"val":"00000000"}]},{"nid":231,"lid":0,"id":56,"portnum":17,"serial":"SMT-DNZH        ","version":"D20170214V1.00  ","mac8":"00:00:00:00:00:00:00:00","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"00000000"},{"port":2,"val":"00000021"},{"port":3,"val":"00000001"},{"port":4,"val":"00000000"},{"port":5,"val":"00000000"},{"port":6,"val":"00000000"},{"port":7,"val":"00000000"},{"port":8,"val":"00000000"},{"port":9,"val":"00000000"},{"port":10,"val":"00000000"},{"port":11,"val":"00000000"},{"port":12,"val":"00000000"},{"port":13,"val":"00000000"},{"port":14,"val":"00000000"},{"port":15,"val":"00000000"},{"port":16,"val":"00000000"}]},{"nid":231,"lid":0,"id":62,"portnum":1,"serial":"MI0606A","version":"V1.0.0","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"}]},{"nid":231,"lid":0,"id":63,"portnum":7,"serial":"SP01A ","version":"V1.0.2","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"0000098f"},{"port":2,"val":"00000009"},{"port":3,"val":"000008b4"},{"port":4,"val":"0000000b"},{"port":5,"val":"00000039"},{"port":6,"val":"00000032"}]}]}
     * otherdevice : {"air_conditioner":{"count":"36","list":[{"outaddr":"1","inaddr":"1","airtype":"0","capacity":"133","allremotecontrol":"200","remotecontrol":"123","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"1","airtype":"0","capacity":"36","allremotecontrol":"128","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"1","airtype":"0","capacity":"68","allremotecontrol":"192","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"9","airtype":"0","capacity":"68","allremotecontrol":"208","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"9","airtype":"0","capacity":"36","allremotecontrol":"208","remotecontrol":"130","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"5","airtype":"0","capacity":"36","allremotecontrol":"144","remotecontrol":"128","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"5","airtype":"0","capacity":"132","allremotecontrol":"216","remotecontrol":"123","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"5","airtype":"0","capacity":"37","allremotecontrol":"192","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"13","airtype":"0","capacity":"68","allremotecontrol":"208","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"13","airtype":"0","capacity":"132","allremotecontrol":"224","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"3","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"3","airtype":"0","capacity":"37","allremotecontrol":"208","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"11","airtype":"0","capacity":"68","allremotecontrol":"176","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"11","airtype":"0","capacity":"68","allremotecontrol":"184","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"7","airtype":"0","capacity":"36","allremotecontrol":"144","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"7","airtype":"0","capacity":"132","allremotecontrol":"216","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"15","airtype":"0","capacity":"36","allremotecontrol":"168","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"15","airtype":"0","capacity":"36","allremotecontrol":"208","remotecontrol":"122","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"2","airtype":"0","capacity":"36","allremotecontrol":"192","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"2","airtype":"0","capacity":"36","allremotecontrol":"216","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"2","airtype":"0","capacity":"36","allremotecontrol":"208","remotecontrol":"126","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"10","airtype":"0","capacity":"132","allremotecontrol":"208","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"10","airtype":"0","capacity":"36","allremotecontrol":"192","remotecontrol":"126","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"6","airtype":"0","capacity":"36","allremotecontrol":"144","remotecontrol":"130","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"6","airtype":"0","capacity":"133","allremotecontrol":"224","remotecontrol":"128","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"14","airtype":"0","capacity":"37","allremotecontrol":"128","remotecontrol":"120","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"14","airtype":"0","capacity":"132","allremotecontrol":"224","remotecontrol":"131","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"4","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"4","airtype":"0","capacity":"37","allremotecontrol":"192","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"4","airtype":"0","capacity":"132","allremotecontrol":"200","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"12","airtype":"0","capacity":"132","allremotecontrol":"208","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"12","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"8","airtype":"0","capacity":"133","allremotecontrol":"208","remotecontrol":"126","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"8","airtype":"0","capacity":"36","allremotecontrol":"192","remotecontrol":"128","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"16","airtype":"0","capacity":"132","allremotecontrol":"224","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"16","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"124","temperature":"0","setmode":"0"}]},"air_detection":[],"camera":[{"serial":"651758036","content":{"category":"CS-C2C-31WFR-B","defence":0,"deviceName":"C2C(651758036)","deviceSerial":"651758036","isEncrypt":1,"jE":"CS-C2C-31WFR-B","jF":1525916155719,"jG":"","jH":"","jI":1,"jJ":0,"jK":0,"jL":[{"ar":0,"cameraNo":1,"deviceSerial":"651758036","jk":"C2C(651758036)","jl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","videoLevel":0,"videoQualityInfos":[{"kN":"均衡","streamType":1,"videoLevel":1},{"kN":"超清","streamType":1,"videoLevel":3},{"kN":"超清","streamType":1,"videoLevel":3}]}],"status":0,"supportExtShort":"1|3|1|1|1|0|1|1|1|1|1|1|3|1|1|16-9|-1|-1|-1|-1|-1|-1|-1|1|-1|0|-1|1|-1|1|1|-1|-1|0|-1|-1|1|-1|-1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|0|-1|-1|1|-1|-1|-1|-1|1|-1|1|-1|-1|0|-1|-1|-1|1|-1|-1|-1|1|1|-1|-1|-1|-1|-1|-1|-1|1|-1|-1|-1|-1|-1|-1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|"}},{"serial":"651758036","content":123},{"serial":"651758036","content":123},{"serial":"651758036","content":123}]}
     */

    private AlldeviceBean alldevice;
    private OtherdeviceBean otherdevice;

    public AlldeviceBean getAlldevice() {
        return alldevice;
    }

    public void setAlldevice(AlldeviceBean alldevice) {
        this.alldevice = alldevice;
    }

    public OtherdeviceBean getOtherdevice() {
        return otherdevice;
    }

    public void setOtherdevice(OtherdeviceBean otherdevice) {
        this.otherdevice = otherdevice;
    }

    public static class AlldeviceBean {
        /**
         * type : data
         * version : D20170624V1.86
         * time : 2018/6/21 21:58:10
         * ipinfor : mac=12:07:22:00:12:20,ip=192.168.1.231,mask=255.255.255.0,route=192.168.1.1,dns=192.168.1.1,udhcpc=off
         * data : [{"nid":231,"lid":0,"id":7,"portnum":3,"serial":"ET-D0201A       ","version":"D20150403V1.03  ","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"10000000"},{"port":2,"val":"10000001"}]},{"nid":231,"lid":0,"id":10,"portnum":1,"serial":"MI0606A","version":"V1.0.0","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"}]},{"nid":231,"lid":0,"id":11,"portnum":9,"serial":"ET-R0816A       ","version":"D20150804V1.11  ","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"00000000"},{"port":2,"val":"00000001"},{"port":3,"val":"00000000"},{"port":4,"val":"00000000"},{"port":5,"val":"00000000"},{"port":6,"val":"00000000"},{"port":7,"val":"00000000"},{"port":8,"val":"00000000"}]},{"nid":231,"lid":0,"id":28,"portnum":5,"serial":"ET-R0416A       ","version":"D20150804V1.10  ","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"00000000"},{"port":2,"val":"00000000"},{"port":3,"val":"00000000"},{"port":4,"val":"00000000"}]},{"nid":231,"lid":0,"id":38,"portnum":9,"serial":"ET-DMX08A       ","version":"D20140303V1.00  ","mac8":"00:00:00:00:00:00:00:00","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"10000000"},{"port":2,"val":"10000000"},{"port":3,"val":"10000000"},{"port":4,"val":"10000000"},{"port":5,"val":"10000000"},{"port":6,"val":"10000000"},{"port":7,"val":"10000000"},{"port":8,"val":"10000000"}]},{"nid":231,"lid":0,"id":44,"portnum":17,"serial":"SMT-CLNET       ","version":"D20170214V1.00  ","mac8":"00:00:00:00:00:00:00:00","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"00000000"},{"port":2,"val":"00000000"},{"port":3,"val":"00000000"},{"port":4,"val":"00000000"},{"port":5,"val":"00000000"},{"port":6,"val":"00000000"},{"port":7,"val":"00000000"},{"port":8,"val":"0000000d"},{"port":9,"val":"00000000"},{"port":10,"val":"00000000"},{"port":11,"val":"00000000"},{"port":12,"val":"00000000"},{"port":13,"val":"00000000"},{"port":14,"val":"00000000"},{"port":15,"val":"00000000"},{"port":16,"val":"00000000"}]},{"nid":231,"lid":0,"id":52,"portnum":17,"serial":"KPS128A","version":"V1.0.2","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"ff000001"},{"port":2,"val":"ff000000"},{"port":3,"val":"ff000000"},{"port":4,"val":"ff000000"},{"port":5,"val":"ff000000"},{"port":6,"val":"ff000000"},{"port":7,"val":"ff000000"},{"port":8,"val":"ff000000"},{"port":9,"val":"ff000000"},{"port":10,"val":"ff000000"},{"port":11,"val":"ff000000"},{"port":12,"val":"ff000000"},{"port":13,"val":"ff000000"},{"port":14,"val":"ff000000"},{"port":15,"val":"ff000000"},{"port":16,"val":"ff000000"}]},{"nid":231,"lid":0,"id":53,"portnum":17,"serial":"SMT-XFZH        ","version":"D20170214V1.00  ","mac8":"00:00:00:00:00:00:00:00","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"00000000"},{"port":2,"val":"00000001"},{"port":3,"val":"00000003"},{"port":4,"val":"00000003"},{"port":5,"val":"0000002d"},{"port":6,"val":"00000014"},{"port":7,"val":"00000080"},{"port":8,"val":"00000000"},{"port":9,"val":"00000000"},{"port":10,"val":"00000000"},{"port":11,"val":"00000000"},{"port":12,"val":"00000000"},{"port":13,"val":"00000000"},{"port":14,"val":"00000000"},{"port":15,"val":"00000000"},{"port":16,"val":"00000000"}]},{"nid":231,"lid":0,"id":56,"portnum":17,"serial":"SMT-DNZH        ","version":"D20170214V1.00  ","mac8":"00:00:00:00:00:00:00:00","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"00000000"},{"port":2,"val":"00000021"},{"port":3,"val":"00000001"},{"port":4,"val":"00000000"},{"port":5,"val":"00000000"},{"port":6,"val":"00000000"},{"port":7,"val":"00000000"},{"port":8,"val":"00000000"},{"port":9,"val":"00000000"},{"port":10,"val":"00000000"},{"port":11,"val":"00000000"},{"port":12,"val":"00000000"},{"port":13,"val":"00000000"},{"port":14,"val":"00000000"},{"port":15,"val":"00000000"},{"port":16,"val":"00000000"}]},{"nid":231,"lid":0,"id":62,"portnum":1,"serial":"MI0606A","version":"V1.0.0","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"}]},{"nid":231,"lid":0,"id":63,"portnum":7,"serial":"SP01A ","version":"V1.0.2","mac8":"ff:ff:ff:ff:ff:ff:ff:ff","ports":[{"port":0,"val":"00000000"},{"port":1,"val":"0000098f"},{"port":2,"val":"00000009"},{"port":3,"val":"000008b4"},{"port":4,"val":"0000000b"},{"port":5,"val":"00000039"},{"port":6,"val":"00000032"}]}]
         */

        private String type;
        private String version;
        private String time;
        private String ipinfor;
        private List<DataBean> data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getIpinfor() {
            return ipinfor;
        }

        public void setIpinfor(String ipinfor) {
            this.ipinfor = ipinfor;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            /**
             * nid : 231
             * lid : 0
             * id : 7
             * portnum : 3
             * serial : ET-D0201A
             * version : D20150403V1.03
             * mac8 : ff:ff:ff:ff:ff:ff:ff:ff
             * ports : [{"port":0,"val":"00000000"},{"port":1,"val":"10000000"},{"port":2,"val":"10000001"}]
             */

            private int nid;
            private int lid;
            private int id;
            private int portnum;
            private String serial;
            private String version;
            private String mac8;
            private List<PortsBean> ports;
            //自定义的属性
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNid() {
                return nid;
            }

            public void setNid(int nid) {
                this.nid = nid;
            }

            public int getLid() {
                return lid;
            }

            public void setLid(int lid) {
                this.lid = lid;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPortnum() {
                return portnum;
            }

            public void setPortnum(int portnum) {
                this.portnum = portnum;
            }

            public String getSerial() {
                return serial;
            }

            public void setSerial(String serial) {
                this.serial = serial;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getMac8() {
                return mac8;
            }

            public void setMac8(String mac8) {
                this.mac8 = mac8;
            }

            public List<PortsBean> getPorts() {
                return ports;
            }

            public void setPorts(List<PortsBean> ports) {
                this.ports = ports;
            }

            public static class PortsBean implements Serializable {
                /**
                 * port : 0
                 * val : 00000000
                 */

                private int port;
                private String val;

                public int getPort() {
                    return port;
                }

                public void setPort(int port) {
                    this.port = port;
                }

                public String getVal() {
                    return val;
                }

                public void setVal(String val) {
                    this.val = val;
                }
            }
        }
    }

    public static class OtherdeviceBean {
        /**
         * air_conditioner : {"count":"36","list":[{"outaddr":"1","inaddr":"1","airtype":"0","capacity":"133","allremotecontrol":"200","remotecontrol":"123","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"1","airtype":"0","capacity":"36","allremotecontrol":"128","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"1","airtype":"0","capacity":"68","allremotecontrol":"192","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"9","airtype":"0","capacity":"68","allremotecontrol":"208","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"9","airtype":"0","capacity":"36","allremotecontrol":"208","remotecontrol":"130","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"5","airtype":"0","capacity":"36","allremotecontrol":"144","remotecontrol":"128","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"5","airtype":"0","capacity":"132","allremotecontrol":"216","remotecontrol":"123","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"5","airtype":"0","capacity":"37","allremotecontrol":"192","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"13","airtype":"0","capacity":"68","allremotecontrol":"208","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"13","airtype":"0","capacity":"132","allremotecontrol":"224","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"3","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"3","airtype":"0","capacity":"37","allremotecontrol":"208","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"11","airtype":"0","capacity":"68","allremotecontrol":"176","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"11","airtype":"0","capacity":"68","allremotecontrol":"184","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"7","airtype":"0","capacity":"36","allremotecontrol":"144","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"7","airtype":"0","capacity":"132","allremotecontrol":"216","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"15","airtype":"0","capacity":"36","allremotecontrol":"168","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"15","airtype":"0","capacity":"36","allremotecontrol":"208","remotecontrol":"122","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"2","airtype":"0","capacity":"36","allremotecontrol":"192","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"2","airtype":"0","capacity":"36","allremotecontrol":"216","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"2","airtype":"0","capacity":"36","allremotecontrol":"208","remotecontrol":"126","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"10","airtype":"0","capacity":"132","allremotecontrol":"208","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"10","airtype":"0","capacity":"36","allremotecontrol":"192","remotecontrol":"126","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"6","airtype":"0","capacity":"36","allremotecontrol":"144","remotecontrol":"130","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"6","airtype":"0","capacity":"133","allremotecontrol":"224","remotecontrol":"128","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"14","airtype":"0","capacity":"37","allremotecontrol":"128","remotecontrol":"120","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"14","airtype":"0","capacity":"132","allremotecontrol":"224","remotecontrol":"131","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"4","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"4","airtype":"0","capacity":"37","allremotecontrol":"192","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"4","airtype":"0","capacity":"132","allremotecontrol":"200","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"12","airtype":"0","capacity":"132","allremotecontrol":"208","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"12","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"8","airtype":"0","capacity":"133","allremotecontrol":"208","remotecontrol":"126","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"8","airtype":"0","capacity":"36","allremotecontrol":"192","remotecontrol":"128","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"16","airtype":"0","capacity":"132","allremotecontrol":"224","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"16","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"124","temperature":"0","setmode":"0"}]}
         * air_detection : []
         * camera : [{"serial":"651758036","content":{"category":"CS-C2C-31WFR-B","defence":0,"deviceName":"C2C(651758036)","deviceSerial":"651758036","isEncrypt":1,"jE":"CS-C2C-31WFR-B","jF":1525916155719,"jG":"","jH":"","jI":1,"jJ":0,"jK":0,"jL":[{"ar":0,"cameraNo":1,"deviceSerial":"651758036","jk":"C2C(651758036)","jl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","videoLevel":0,"videoQualityInfos":[{"kN":"均衡","streamType":1,"videoLevel":1},{"kN":"超清","streamType":1,"videoLevel":3},{"kN":"超清","streamType":1,"videoLevel":3}]}],"status":0,"supportExtShort":"1|3|1|1|1|0|1|1|1|1|1|1|3|1|1|16-9|-1|-1|-1|-1|-1|-1|-1|1|-1|0|-1|1|-1|1|1|-1|-1|0|-1|-1|1|-1|-1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|0|-1|-1|1|-1|-1|-1|-1|1|-1|1|-1|-1|0|-1|-1|-1|1|-1|-1|-1|1|1|-1|-1|-1|-1|-1|-1|-1|1|-1|-1|-1|-1|-1|-1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|"}},{"serial":"651758036","content":123},{"serial":"651758036","content":123},{"serial":"651758036","content":123}]
         */

        private AirConditionerBean air_conditioner;
        private List<AirDetectionBean> air_detection;
        private List<CameraBean> camera;

        public AirConditionerBean getAir_conditioner() {
            return air_conditioner;
        }

        public void setAir_conditioner(AirConditionerBean air_conditioner) {
            this.air_conditioner = air_conditioner;
        }

        public List<AirDetectionBean> getAir_detection() {
            return air_detection;
        }

        public void setAir_detection(List<AirDetectionBean> air_detection) {
            this.air_detection = air_detection;
        }

        public List<CameraBean> getCamera() {
            return camera;
        }

        public void setCamera(List<CameraBean> camera) {
            this.camera = camera;
        }

        public static class AirConditionerBean {
            /**
             * count : 36
             * list : [{"outaddr":"1","inaddr":"1","airtype":"0","capacity":"133","allremotecontrol":"200","remotecontrol":"123","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"1","airtype":"0","capacity":"36","allremotecontrol":"128","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"1","airtype":"0","capacity":"68","allremotecontrol":"192","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"9","airtype":"0","capacity":"68","allremotecontrol":"208","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"9","airtype":"0","capacity":"36","allremotecontrol":"208","remotecontrol":"130","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"5","airtype":"0","capacity":"36","allremotecontrol":"144","remotecontrol":"128","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"5","airtype":"0","capacity":"132","allremotecontrol":"216","remotecontrol":"123","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"5","airtype":"0","capacity":"37","allremotecontrol":"192","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"13","airtype":"0","capacity":"68","allremotecontrol":"208","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"13","airtype":"0","capacity":"132","allremotecontrol":"224","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"3","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"3","airtype":"0","capacity":"37","allremotecontrol":"208","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"11","airtype":"0","capacity":"68","allremotecontrol":"176","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"11","airtype":"0","capacity":"68","allremotecontrol":"184","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"7","airtype":"0","capacity":"36","allremotecontrol":"144","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"7","airtype":"0","capacity":"132","allremotecontrol":"216","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"15","airtype":"0","capacity":"36","allremotecontrol":"168","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"15","airtype":"0","capacity":"36","allremotecontrol":"208","remotecontrol":"122","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"2","airtype":"0","capacity":"36","allremotecontrol":"192","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"2","airtype":"0","capacity":"36","allremotecontrol":"216","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"2","airtype":"0","capacity":"36","allremotecontrol":"208","remotecontrol":"126","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"10","airtype":"0","capacity":"132","allremotecontrol":"208","remotecontrol":"127","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"10","airtype":"0","capacity":"36","allremotecontrol":"192","remotecontrol":"126","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"6","airtype":"0","capacity":"36","allremotecontrol":"144","remotecontrol":"130","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"6","airtype":"0","capacity":"133","allremotecontrol":"224","remotecontrol":"128","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"14","airtype":"0","capacity":"37","allremotecontrol":"128","remotecontrol":"120","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"14","airtype":"0","capacity":"132","allremotecontrol":"224","remotecontrol":"131","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"4","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"3","inaddr":"4","airtype":"0","capacity":"37","allremotecontrol":"192","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"4","airtype":"0","capacity":"132","allremotecontrol":"200","remotecontrol":"124","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"12","airtype":"0","capacity":"132","allremotecontrol":"208","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"12","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"125","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"8","airtype":"0","capacity":"133","allremotecontrol":"208","remotecontrol":"126","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"8","airtype":"0","capacity":"36","allremotecontrol":"192","remotecontrol":"128","temperature":"0","setmode":"0"},{"outaddr":"1","inaddr":"16","airtype":"0","capacity":"132","allremotecontrol":"224","remotecontrol":"129","temperature":"0","setmode":"0"},{"outaddr":"2","inaddr":"16","airtype":"0","capacity":"36","allremotecontrol":"176","remotecontrol":"124","temperature":"0","setmode":"0"}]
             */

            private String count;
            private List<ListBean> list;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean implements Serializable{
                /**
                 * outaddr : 1
                 * inaddr : 1
                 * airtype : 0
                 * capacity : 133
                 * allremotecontrol : 200
                 * remotecontrol : 123
                 * temperature : 0
                 * setmode : 0
                 */

                private String outaddr;
                private String inaddr;
                private String airtype;
                private String capacity;
                private String allremotecontrol;
                private String remotecontrol;
                private String temperature;
                private String setmode;

                public String getOutaddr() {
                    return outaddr;
                }

                public void setOutaddr(String outaddr) {
                    this.outaddr = outaddr;
                }

                public String getInaddr() {
                    return inaddr;
                }

                public void setInaddr(String inaddr) {
                    this.inaddr = inaddr;
                }

                public String getAirtype() {
                    return airtype;
                }

                public void setAirtype(String airtype) {
                    this.airtype = airtype;
                }

                public String getCapacity() {
                    return capacity;
                }

                public void setCapacity(String capacity) {
                    this.capacity = capacity;
                }

                public String getAllremotecontrol() {
                    return allremotecontrol;
                }

                public void setAllremotecontrol(String allremotecontrol) {
                    this.allremotecontrol = allremotecontrol;
                }

                public String getRemotecontrol() {
                    return remotecontrol;
                }

                public void setRemotecontrol(String remotecontrol) {
                    this.remotecontrol = remotecontrol;
                }

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getSetmode() {
                    return setmode;
                }

                public void setSetmode(String setmode) {
                    this.setmode = setmode;
                }
            }
        }

        public static class AirDetectionBean {
            /**
             * detail : [{"nindex":"1","type":"48","id":"1","company":"24","status":"1","length":"2","data":"864.0"},{"nindex":"2","type":"201","id":"1","company":"40","status":"1","length":"1","data":"27.0"},{"nindex":"3","type":"202","id":"1","company":"48","status":"1","length":"1","data":"51.0"},{"nindex":"4","type":"216","id":"1","company":"152","status":"1","length":"1","data":"4.0"},{"nindex":"5","type":"217","id":"1","company":"0","status":"1","length":"1","data":"3.0"}]
             * idcode : 50:00:21:9b:39:bb
             * time : 18-4-18 22:54:28_0
             */

            private String idcode;
            private String time;
            private List<DetailBean> detail;

            public String getIdcode() {
                return idcode;
            }

            public void setIdcode(String idcode) {
                this.idcode = idcode;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public List<DetailBean> getDetail() {
                return detail;
            }

            public void setDetail(List<DetailBean> detail) {
                this.detail = detail;
            }

            public static class DetailBean {
                /**
                 * nindex : 1
                 * type : 48
                 * id : 1
                 * company : 24
                 * status : 1
                 * length : 2
                 * data : 864.0
                 */

                private String nindex;
                private String type;
                private String id;
                private String company;
                private String status;
                private String length;
                private String data;

                public String getNindex() {
                    return nindex;
                }

                public void setNindex(String nindex) {
                    this.nindex = nindex;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getLength() {
                    return length;
                }

                public void setLength(String length) {
                    this.length = length;
                }

                public String getData() {
                    return data;
                }

                public void setData(String data) {
                    this.data = data;
                }
            }
        }

        public static class CameraBean implements Serializable{
            /**
             * serial : 651758036
             * content : {"category":"CS-C2C-31WFR-B","defence":0,"deviceName":"C2C(651758036)","deviceSerial":"651758036","isEncrypt":1,"jE":"CS-C2C-31WFR-B","jF":1525916155719,"jG":"","jH":"","jI":1,"jJ":0,"jK":0,"jL":[{"ar":0,"cameraNo":1,"deviceSerial":"651758036","jk":"C2C(651758036)","jl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","videoLevel":0,"videoQualityInfos":[{"kN":"均衡","streamType":1,"videoLevel":1},{"kN":"超清","streamType":1,"videoLevel":3},{"kN":"超清","streamType":1,"videoLevel":3}]}],"status":0,"supportExtShort":"1|3|1|1|1|0|1|1|1|1|1|1|3|1|1|16-9|-1|-1|-1|-1|-1|-1|-1|1|-1|0|-1|1|-1|1|1|-1|-1|0|-1|-1|1|-1|-1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|0|-1|-1|1|-1|-1|-1|-1|1|-1|1|-1|-1|0|-1|-1|-1|1|-1|-1|-1|1|1|-1|-1|-1|-1|-1|-1|-1|1|-1|-1|-1|-1|-1|-1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|"}
             */
//
            private String serial;
//            private ContentBean content;

            public String getSerial() {
                return serial;
            }

            public void setSerial(String serial) {
                this.serial = serial;
            }
//
//            public ContentBean getContent() {
//                return content;
//            }
//
//            public void setContent(ContentBean content) {
//                this.content = content;
//            }
//
//            public static class ContentBean {
//                /**
//                 * category : CS-C2C-31WFR-B
//                 * defence : 0
//                 * deviceName : C2C(651758036)
//                 * deviceSerial : 651758036
//                 * isEncrypt : 1
//                 * jE : CS-C2C-31WFR-B
//                 * jF : 1525916155719
//                 * jG :
//                 * jH :
//                 * jI : 1
//                 * jJ : 0
//                 * jK : 0
//                 * jL : [{"ar":0,"cameraNo":1,"deviceSerial":"651758036","jk":"C2C(651758036)","jl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","videoLevel":0,"videoQualityInfos":[{"kN":"均衡","streamType":1,"videoLevel":1},{"kN":"超清","streamType":1,"videoLevel":3},{"kN":"超清","streamType":1,"videoLevel":3}]}]
//                 * status : 0
//                 * supportExtShort : 1|3|1|1|1|0|1|1|1|1|1|1|3|1|1|16-9|-1|-1|-1|-1|-1|-1|-1|1|-1|0|-1|1|-1|1|1|-1|-1|0|-1|-1|1|-1|-1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|0|-1|-1|1|-1|-1|-1|-1|1|-1|1|-1|-1|0|-1|-1|-1|1|-1|-1|-1|1|1|-1|-1|-1|-1|-1|-1|-1|1|-1|-1|-1|-1|-1|-1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|
//                 */
//
//                private String category;
//                private int defence;
//                private String deviceName;
//                private String deviceSerial;
//                private int isEncrypt;
//                private String jE;
//                private long jF;
//                private String jG;
//                private String jH;
//                private int jI;
//                private int jJ;
//                private int jK;
//                private int status;
//                private String supportExtShort;
//                private List<JLBean> jL;
//
//                public String getCategory() {
//                    return category;
//                }
//
//                public void setCategory(String category) {
//                    this.category = category;
//                }
//
//                public int getDefence() {
//                    return defence;
//                }
//
//                public void setDefence(int defence) {
//                    this.defence = defence;
//                }
//
//                public String getDeviceName() {
//                    return deviceName;
//                }
//
//                public void setDeviceName(String deviceName) {
//                    this.deviceName = deviceName;
//                }
//
//                public String getDeviceSerial() {
//                    return deviceSerial;
//                }
//
//                public void setDeviceSerial(String deviceSerial) {
//                    this.deviceSerial = deviceSerial;
//                }
//
//                public int getIsEncrypt() {
//                    return isEncrypt;
//                }
//
//                public void setIsEncrypt(int isEncrypt) {
//                    this.isEncrypt = isEncrypt;
//                }
//
//                public String getJE() {
//                    return jE;
//                }
//
//                public void setJE(String jE) {
//                    this.jE = jE;
//                }
//
//                public long getJF() {
//                    return jF;
//                }
//
//                public void setJF(long jF) {
//                    this.jF = jF;
//                }
//
//                public String getJG() {
//                    return jG;
//                }
//
//                public void setJG(String jG) {
//                    this.jG = jG;
//                }
//
//                public String getJH() {
//                    return jH;
//                }
//
//                public void setJH(String jH) {
//                    this.jH = jH;
//                }
//
//                public int getJI() {
//                    return jI;
//                }
//
//                public void setJI(int jI) {
//                    this.jI = jI;
//                }
//
//                public int getJJ() {
//                    return jJ;
//                }
//
//                public void setJJ(int jJ) {
//                    this.jJ = jJ;
//                }
//
//                public int getJK() {
//                    return jK;
//                }
//
//                public void setJK(int jK) {
//                    this.jK = jK;
//                }
//
//                public int getStatus() {
//                    return status;
//                }
//
//                public void setStatus(int status) {
//                    this.status = status;
//                }
//
//                public String getSupportExtShort() {
//                    return supportExtShort;
//                }
//
//                public void setSupportExtShort(String supportExtShort) {
//                    this.supportExtShort = supportExtShort;
//                }
//
//                public List<JLBean> getJL() {
//                    return jL;
//                }
//
//                public void setJL(List<JLBean> jL) {
//                    this.jL = jL;
//                }
//
//                public static class JLBean {
//                    /**
//                     * ar : 0
//                     * cameraNo : 1
//                     * deviceSerial : 651758036
//                     * jk : C2C(651758036)
//                     * jl : https://i.ys7.com/assets/imgs/public/homeDevice.jpeg
//                     * videoLevel : 0
//                     * videoQualityInfos : [{"kN":"均衡","streamType":1,"videoLevel":1},{"kN":"超清","streamType":1,"videoLevel":3},{"kN":"超清","streamType":1,"videoLevel":3}]
//                     */
//
//                    private int ar;
//                    private int cameraNo;
//                    private String deviceSerial;
//                    private String jk;
//                    private String jl;
//                    private int videoLevel;
//                    private List<VideoQualityInfosBean> videoQualityInfos;
//
//                    public int getAr() {
//                        return ar;
//                    }
//
//                    public void setAr(int ar) {
//                        this.ar = ar;
//                    }
//
//                    public int getCameraNo() {
//                        return cameraNo;
//                    }
//
//                    public void setCameraNo(int cameraNo) {
//                        this.cameraNo = cameraNo;
//                    }
//
//                    public String getDeviceSerial() {
//                        return deviceSerial;
//                    }
//
//                    public void setDeviceSerial(String deviceSerial) {
//                        this.deviceSerial = deviceSerial;
//                    }
//
//                    public String getJk() {
//                        return jk;
//                    }
//
//                    public void setJk(String jk) {
//                        this.jk = jk;
//                    }
//
//                    public String getJl() {
//                        return jl;
//                    }
//
//                    public void setJl(String jl) {
//                        this.jl = jl;
//                    }
//
//                    public int getVideoLevel() {
//                        return videoLevel;
//                    }
//
//                    public void setVideoLevel(int videoLevel) {
//                        this.videoLevel = videoLevel;
//                    }
//
//                    public List<VideoQualityInfosBean> getVideoQualityInfos() {
//                        return videoQualityInfos;
//                    }
//
//                    public void setVideoQualityInfos(List<VideoQualityInfosBean> videoQualityInfos) {
//                        this.videoQualityInfos = videoQualityInfos;
//                    }
//
//                    public static class VideoQualityInfosBean {
//                        /**
//                         * kN : 均衡
//                         * streamType : 1
//                         * videoLevel : 1
//                         */
//
//                        private String kN;
//                        private int streamType;
//                        private int videoLevel;
//
//                        public String getKN() {
//                            return kN;
//                        }
//
//                        public void setKN(String kN) {
//                            this.kN = kN;
//                        }
//
//                        public int getStreamType() {
//                            return streamType;
//                        }
//
//                        public void setStreamType(int streamType) {
//                            this.streamType = streamType;
//                        }
//
//                        public int getVideoLevel() {
//                            return videoLevel;
//                        }
//
//                        public void setVideoLevel(int videoLevel) {
//                            this.videoLevel = videoLevel;
//                        }
//                    }
//                }
//            }
//        }
        }
    }
}
