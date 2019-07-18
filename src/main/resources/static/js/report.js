$(function(){
	$('#selectCityId').change(function(){
		var cityId = $('#selectCityId').val();
		var url = '/report/cityId/' + cityId;
		//页面跳转
		window.location.href = url;
	});
});