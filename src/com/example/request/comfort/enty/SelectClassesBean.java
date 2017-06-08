package com.example.request.comfort.enty;

import java.util.List;

public class SelectClassesBean {
    	 /**
         * enroll_closing_at : 2017-03-31 21:39:33
         * execute_opening_at : null
         * predict_closing_at : 2017-03-31 21:39:33
         * uuid : edaf14c0-1e77-11e7-bc9e-0242ac120003
         * roles : [{"priority":"1","id":"4","name":"学员"}]
         * docs : [{"content":"内容","purpose":"manual","format":"html","id":8,"name":"文档1"}]
         * predict_opening_at : 2017-03-31 21:39:33
         * state : pending
         * specs : {"room":"1023","end_date":"2017-02-11","morph":"conference","host_center":"承办中心","expected_people_count":123456,"host_unit":"主办单位","area":"第三培训区","report_date":"2017-02-11","contact":"123456789","meeting_date":"2017-02-11","address":"第1培训区3号","creator":"3","id":"edaf14c0-1e77-11e7-bc9e-0242ac120003","name":"会议名称"}
         * update_time : 2017-04-11 05:30:09
         * enroll_opening_at : 2017-03-31 21:39:33
         * noCheckCount : 0
         * places : [{"modality":"virtual","comment":"详细说明","state":"enabled","id":3,"name":"西街广场"}]
         * created_time : 2017-04-11 05:30:09
         * category_name : 培训
         * category_id : conference
         * execute_closing_at : null
         * name : 培训班
         */

//        private String enroll_closing_at;
////        private Object execute_opening_at;
//        private String predict_closing_at;
        private String uuid;
//        private String predict_opening_at;
        private String state;
        public List<SpecsBean> specs;
//        private String update_time;
//        private String enroll_opening_at;
//        private int noCheckCount;
//        private String created_time;
//        private String category_name;
//        private String category_id;
//        private Object execute_closing_at;
        private String name;
        private String my_state;
        
        

        public String getMy_state() {
			return my_state;
		}

		public void setMy_state(String my_state) {
			this.my_state = my_state;
		}

//		public String getEnroll_closing_at() {
//            return enroll_closing_at;
//        }
//
//        public void setEnroll_closing_at(String enroll_closing_at) {
//            this.enroll_closing_at = enroll_closing_at;
//        }

//        public Object getExecute_opening_at() {
//            return execute_opening_at;
//        }
//
//        public void setExecute_opening_at(Object execute_opening_at) {
//            this.execute_opening_at = execute_opening_at;
//        }

//        public String getPredict_closing_at() {
//            return predict_closing_at;
//        }
//
//        public void setPredict_closing_at(String predict_closing_at) {
//            this.predict_closing_at = predict_closing_at;
//        }
//
        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
//
//        public String getPredict_opening_at() {
//            return predict_opening_at;
//        }
//
//        public void setPredict_opening_at(String predict_opening_at) {
//            this.predict_opening_at = predict_opening_at;
//        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<SpecsBean> getSpecs() {
            return specs;
        }

        public void setSpecs(List<SpecsBean> specs) {
            this.specs = specs;
        }

//        public String getUpdate_time() {
//            return update_time;
//        }
//
//        public void setUpdate_time(String update_time) {
//            this.update_time = update_time;
//        }
//
//        public String getEnroll_opening_at() {
//            return enroll_opening_at;
//        }
//
//        public void setEnroll_opening_at(String enroll_opening_at) {
//            this.enroll_opening_at = enroll_opening_at;
//        }
//
//        public int getNoCheckCount() {
//            return noCheckCount;
//        }
//
//        public void setNoCheckCount(int noCheckCount) {
//            this.noCheckCount = noCheckCount;
//        }
//
//        public String getCreated_time() {
//            return created_time;
//        }
//
//        public void setCreated_time(String created_time) {
//            this.created_time = created_time;
//        }
//
//        public String getCategory_name() {
//            return category_name;
//        }
//
//        public void setCategory_name(String category_name) {
//            this.category_name = category_name;
//        }
//
//        public String getCategory_id() {
//            return category_id;
//        }
//
//        public void setCategory_id(String category_id) {
//            this.category_id = category_id;
//        }

//        public Object getExecute_closing_at() {
//            return execute_closing_at;
//        }
//
//        public void setExecute_closing_at(Object execute_closing_at) {
//            this.execute_closing_at = execute_closing_at;
//        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

//        public List<RolesBean> getRoles() {
//            return roles;
//        }
//
//        public void setRoles(List<RolesBean> roles) {
//            this.roles = roles;
//        }
//
//        public List<DocsBean> getDocs() {
//            return docs;
//        }
//
//        public void setDocs(List<DocsBean> docs) {
//            this.docs = docs;
//        }

//        public List<PlacesBean> getPlaces() {
//            return places;
//        }
//
//        public void setPlaces(List<PlacesBean> places) {
//            this.places = places;
//        }

//		@Override
//		public String toString() {
//			return "SelectClassesBean [enroll_closing_at=" + enroll_closing_at
//					+ ", predict_closing_at=" + predict_closing_at + ", uuid="
//					+ uuid + ", predict_opening_at=" + predict_opening_at
//					+ ", state=" + state + ", specs=" + specs
//					+ ", update_time=" + update_time + ", enroll_opening_at="
//					+ enroll_opening_at + ", noCheckCount=" + noCheckCount
//					+ ", created_time=" + created_time + ", category_name="
//					+ category_name + ", category_id=" + category_id
//					+ ", name="
//					+ name + "]";
//		}    
		
		public static class SpecsBean{
			private String name;
			private String value;
			private String title;
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getValue() {
				return value;
			}
			public void setValue(String value) {
				this.value = value;
			}
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}
			
			
		}
		
//		public static class SpecsBean {
//            /**
//             * room : 1023
//             * end_date : 2017-02-11
//             * morph : conference
//             * host_center : 承办中心
//             * expected_people_count : 123456
//             * host_unit : 主办单位
//             * area : 第三培训区
//             * report_date : 2017-02-11
//             * contact : 123456789
//             * meeting_date : 2017-02-11
//             * address : 第1培训区3号
//             * creator : 3
//             * id : edaf14c0-1e77-11e7-bc9e-0242ac120003
//             * name : 会议名称
//             */
//
//            private String room;
//            private String end_date;
//            private String morph;
//            private String host_center;
//            private int expected_people_count;
//            private String host_unit;
//            private String area;
//            private String report_date;
//            private String contact;
//            private String meeting_date;
//            private String address;
//            private String creator;
//            private String id;
//            private String name;
//
//            public String getRoom() {
//                return room;
//            }
//
//            public void setRoom(String room) {
//                this.room = room;
//            }
//
//            public String getEnd_date() {
//                return end_date;
//            }
//
//            public void setEnd_date(String end_date) {
//                this.end_date = end_date;
//            }
//
//            public String getMorph() {
//                return morph;
//            }
//
//            public void setMorph(String morph) {
//                this.morph = morph;
//            }
//
//            public String getHost_center() {
//                return host_center;
//            }
//
//            public void setHost_center(String host_center) {
//                this.host_center = host_center;
//            }
//
//            public int getExpected_people_count() {
//                return expected_people_count;
//            }
//
//            public void setExpected_people_count(int expected_people_count) {
//                this.expected_people_count = expected_people_count;
//            }
//
//            public String getHost_unit() {
//                return host_unit;
//            }
//
//            public void setHost_unit(String host_unit) {
//                this.host_unit = host_unit;
//            }
//
//            public String getArea() {
//                return area;
//            }
//
//            public void setArea(String area) {
//                this.area = area;
//            }
//
//            public String getReport_date() {
//                return report_date;
//            }
//
//            public void setReport_date(String report_date) {
//                this.report_date = report_date;
//            }
//
//            public String getContact() {
//                return contact;
//            }
//
//            public void setContact(String contact) {
//                this.contact = contact;
//            }
//
//            public String getMeeting_date() {
//                return meeting_date;
//            }
//
//            public void setMeeting_date(String meeting_date) {
//                this.meeting_date = meeting_date;
//            }
//
//            public String getAddress() {
//                return address;
//            }
//
//            public void setAddress(String address) {
//                this.address = address;
//            }
//
//            public String getCreator() {
//                return creator;
//            }
//
//            public void setCreator(String creator) {
//                this.creator = creator;
//            }
//
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//			@Override
//			public String toString() {
//				return "SpecsBean [room=" + room + ", end_date=" + end_date
//						+ ", morph=" + morph + ", host_center=" + host_center
//						+ ", expected_people_count=" + expected_people_count
//						+ ", host_unit=" + host_unit + ", area=" + area
//						+ ", report_date=" + report_date + ", contact="
//						+ contact + ", meeting_date=" + meeting_date
//						+ ", address=" + address + ", creator=" + creator
//						+ ", id=" + id + ", name=" + name + "]";
//			}
//        }
}
