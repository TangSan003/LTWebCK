$.ajax({
                    url : "/getDatareport",
                    success : function(result) {
                        var seri = [];
                    
                        var data = [];
                      
                        var datacolumn1 = [];
                        var datacolumn2 = [] ;
                        var name_product = [];
                        console.log(result);
                        for(var i=0;i<result.length;i++){
                        	var name = {};
                        	name = result[i].name;
                        	name_product.push(name);
                        }
                        for(var i=0;i<result.length;i++){
                        	var colum = {};
                        	colum = result[i].sum;
                        	datacolumn1.push(colum);
                        }
                        for(var i=0;i<result.length;i++){
                        	var colum = {};
                        	colum = result[i].count;
                        	datacolumn2.push(colum);
                        }
                        
                        for(var i=0;i<result.length;i++){
                        	var object = {};
                        	object.name = result[i].name;
                        	object.y = result[i].sum;
                        	data.push(object);
                        }
                        
                       var seriObject = {
                    		   name :'Sum Price',
                    		   colorByPoint:true , 
                    		   data :data , 
                    		   showInLegend: true
                       }
                       
                    
                       seri.push(seriObject);
                       drawColumnChart(datacolumn1 , datacolumn2 ,name_product);
                       drawPieChart(seri);
                       
                    }
                });
 
  function drawColumnChart(datacolumn1 , datacolumn2 ,name_product){
 	Highcharts.chart('container', {

    chart: {
        type: 'column',
        styledMode: true
    },

    title: {
        text: 'Danh thu theo từng sản phẩm'
    },
		xAxis :{categories : name_product},
    yAxis: [{
        className: 'highcharts-color-0',
        title: {
            text: 'Giá $'
        }
    }, {
        className: 'highcharts-color-1',
        opposite: true,
        title: {
            text: 'Số lượng '
        }
    }],

    plotOptions: {
        column: {
            borderRadius: 5
        }
    },
	 
    series: [{
    		name : 'Tổng giá' ,
        	data : datacolumn1
    }, {
    		name : 'Số lượng bán ra ' ,
        	data : datacolumn2,
       	 yAxis: 1
    }]

});
	}
 
 function drawPieChart(seri){
    	Highcharts.chart('piechart', {
            chart: {
            	type: 'pie',
                styledMode: true
            },

            title: {
                text: 'Pie point CSS'
            },
            tooltip: {
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} $$$</b>||<b>{point.percentage:.1f}%</b></td></tr>',
            },
            series:seri
        });
    }
    