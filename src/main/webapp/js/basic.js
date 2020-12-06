let header = new Vue({
	el: "#navbar",
	data: {
		userInfo: {},
		login: false
	},
	created: function(){
		axios.get("../user/checkLogin").then( rt => {
			if (rt.status == 200 && rt.data.code == 200) {
				this.login = true;
				this.userInfo = rt.data.data;
				return;
			}
			this.login = false;
			userInfo = {};
		})
	}
})