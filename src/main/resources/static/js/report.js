$(function () {
    $("#selectCityId").change(function () {
        var cityId = $("#selectCityId").val();
        var url = '/report/cityId/' + cityId;
        window.location.href = url;
    });
});