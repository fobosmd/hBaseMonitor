<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/bootstrap-table.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-table.min.js"></script>
</head>

<body>

    <h1>Monitoring</h1>
    <p>Period of time in minutes to show.</p>
    <div id="toolbar">
        <input type="number" id="periodInput" name="periodInput" class="input-sm" min="1" max="60" value="1">
        <button id="applyPeriodButton" class="btn btn-default">Apply</button> <span id="periodSpan">test</span>
    </div>
    <table id="msgTable"
           data-toggle="table"
           data-toolbar="#toolbar"
           data-show-columns="true"
           data-id-field="timestamp">
        <thead>
        <tr>
            <th data-field="timestamp">TimeStamp</th>
            <th data-field="transactionType">Type</th>
            <th data-field="col0">Col 00</th>
            <th data-field="col1">Col 01</th>
            <th data-field="col2">Col 02</th>
            <th data-field="col3">Col 03</th>
            <th data-field="col4">Col 04</th>
            <th data-field="col5">Col 05</th>
            <th data-field="col6">Col 06</th>
            <th data-field="col7">Col 07</th>
            <th data-field="col8">Col 08</th>
            <th data-field="col9">Col 09</th>
            <th data-field="col10">Col 10</th>

            <th data-field="col11">Col 11</th>
            <th data-field="col12">Col 12</th>
            <th data-field="col13">Col 13</th>
            <th data-field="col14">Col 14</th>
            <th data-field="col15">Col 15</th>
            <th data-field="col16">Col 16</th>
            <th data-field="col17">Col 17</th>
            <th data-field="col18">Col 18</th>
            <th data-field="col19">Col 19</th>
            <th data-field="col20">Col 20</th>

            <th data-field="col21">Col 21</th>
            <th data-field="col22">Col 22</th>
            <th data-field="col23">Col 23</th>
            <th data-field="col24">Col 24</th>
            <th data-field="col25">Col 25</th>
            <th data-field="col26">Col 26</th>
            <th data-field="col27">Col 27</th>
            <th data-field="col28">Col 28</th>
            <th data-field="col29">Col 29</th>
            <th data-field="col30">Col 30</th>

            <th data-field="col31">Col 31</th>
            <th data-field="col32">Col 32</th>
            <th data-field="col33">Col 33</th>
            <th data-field="col34">Col 34</th>
            <th data-field="col35">Col 35</th>
            <th data-field="col36">Col 36</th>
            <th data-field="col37">Col 37</th>
            <th data-field="col38">Col 38</th>
            <th data-field="col39">Col 39</th>
            <th data-field="col40">Col 40</th>

            <th data-field="col41">Col 41</th>
            <th data-field="col42">Col 42</th>
            <th data-field="col43">Col 43</th>
            <th data-field="col44">Col 44</th>
            <th data-field="col45">Col 45</th>
            <th data-field="col46">Col 46</th>
            <th data-field="col47">Col 47</th>
            <th data-field="col48">Col 48</th>
            <th data-field="col49">Col 49</th>
            <th data-field="col50">Col 50</th>

            <th data-field="col61">Col 61</th>
            <th data-field="col62">Col 62</th>
            <th data-field="col63">Col 63</th>
            <th data-field="col64">Col 64</th>
            <th data-field="col65">Col 65</th>
            <th data-field="col66">Col 66</th>
            <th data-field="col67">Col 67</th>
            <th data-field="col68">Col 68</th>
            <th data-field="col69">Col 69</th>
            <th data-field="col70">Col 70</th>

            <th data-field="col71">Col 71</th>
            <th data-field="col72">Col 72</th>
            <th data-field="col73">Col 73</th>
            <th data-field="col74">Col 74</th>
            <th data-field="col75">Col 75</th>
            <th data-field="col76">Col 76</th>
            <th data-field="col77">Col 77</th>
            <th data-field="col78">Col 78</th>
            <th data-field="col79">Col 79</th>
            <th data-field="col80">Col 80</th>

            <th data-field="col81">Col 81</th>
            <th data-field="col82">Col 82</th>
            <th data-field="col83">Col 3</th>
            <th data-field="col84">Col 84</th>
            <th data-field="col85">Col 85</th>
            <th data-field="col86">Col 86</th>
            <th data-field="col87">Col 87</th>
            <th data-field="col88">Col 88</th>
            <th data-field="col89">Col 89</th>
            <th data-field="col90">Col 90</th>

            <th data-field="col91">Col 91</th>
            <th data-field="col92">Col 92</th>
            <th data-field="col93">Col 93</th>
            <th data-field="col94">Col 94</th>
            <th data-field="col95">Col 95</th>
            <th data-field="col96">Col 96</th>
            <th data-field="col97">Col 97</th>
            <th data-field="col98">Col 98</th>
            <th data-field="col99">Col 99</th>
        </tr>
        </thead>
    </table>

<script>
    var ajaxQueryInterval = 5000;
    var periodToShow = 60000;
    var lastTimestamp = new Date().getTime() - periodToShow;
    var colCount = 100;
    var colPrefix = 'col';
    var $table = $('#msgTable');
    var $button = $('#applyPeriodButton');
    var $input = $('#periodInput');
    var $span = $('#periodSpan');

    printPeriod(periodToShow);

    function insertRow(msg){
        var row = {timestamp : msg.timestamp, transactionType : msg.transactionType};

        for (var i = 0; i < colCount; i++) {
            var colName = colPrefix + i;
            var val = msg.data[colName];

            if (val === undefined) {
                val = '';
            }
            row[colName] = val;
        }
        $table.bootstrapTable('insertRow', {index: 0, row: row});
    }

    function printPeriod(period){
        $span.text('Period is '+ period / 60000 + ' minutes.');
    }

    function removeOldRows(){
        var periodToRemove = new Date().getTime() - periodToShow;
        var timestampsToRemove = [];
        var rows = $table.bootstrapTable('getData');

        $.each(rows, function(key, row) {
            if (row.timestamp < periodToRemove) {
                timestampsToRemove.push(row.timestamp)
            }
        });
        $table.bootstrapTable('remove', {
             field: 'timestamp',
             values: timestampsToRemove
        });
    }

    $(function () {
        $button.click(function () {
            var val = $input.val();
            if (!$.isNumeric(val)) {
                alert('Period should be a number');
                return;
            }
            if (val < 1 || val > 60) {
                alert('Period should be between 1 and 60');
                return;
            }
            periodToShow = val * 60000;
            printPeriod(periodToShow);
        });
    });

    setInterval(function() {
        $.ajax({
            contentType : 'application/json; charset=utf-8',
            type: 'post',
            url: '/msglist',
            dataType : 'json',
            data : JSON.stringify(lastTimestamp + 1),
            success : function(msgs){
                if (msgs.length > 0) {
                    lastTimestamp = msgs[msgs.length - 1].timestamp;
                }
                $.each(msgs, function(key, msg) {
                    insertRow(msg);
                });
                removeOldRows();
            },
            error : function(){
                $(this).html("Error!");
            }
        });
    }, ajaxQueryInterval);
</script>
</body>

