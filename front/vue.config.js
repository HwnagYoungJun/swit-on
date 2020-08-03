module.exports = {
	css: {
		loaderOptions: {
			sass: {
				prependData: `
                @import "~@/assets/scss/_variables.scss";
                @import "~@/assets/scss/_mixins.scss";
                `,
			},
			// css: {
			// 	prependData: `
			// 	@import "@/assets/css/reset.css";
			// 	@import "@/assets/css/common.css";
			// 	`,
			// },
		},
	},
};
