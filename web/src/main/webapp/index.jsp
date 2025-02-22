<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
    <title>TMS - Main</title>
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
<div class="header-container">
    <span>Traffic Management System</span>

    <%
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(new Date());
    %>

    <span>Today : <%= formattedDate %></span>
</div>
<hr>

<div class="container">
    <div class="content">
        <table>
            <thead>
            <tr>
                <th class="table-side-header header-1">Time Range (24h)</th>
                <th>00:00-07:00</th>
                <th>07:00-14:00</th>
                <th>14:00-19:00</th>
                <th>19:00-00:00</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th class="table-side-header header-2">Average vehicle Speed(Kmph): Road 1</th>
                <td id="r1_t1">0.0</td>
                <td id="r1_t2">0.0</td>
                <td id="r1_t3">0.0</td>
                <td id="r1_t4">0.0</td>
            </tr>
            <tr>
                <th class="table-side-header header-2">Average vehicle Speed(Kmph): Road 2</th>
                <td id="r2_t1">0.0</td>
                <td id="r2_t2">0.0</td>
                <td id="r2_t3">0.0</td>
                <td id="r2_t4">0.0</td>
            </tr>
            </tbody>
        </table>

        <hr>


        <!-- Traffic Patterns -->
        <h4>Traffic Patterns</h4>
        <table>
            <thead>
            <tr>
                <th class="table-side-header header-1">Time Period</th>
                <th>00:00-06:00</th>
                <th>06:00-12:00</th>
                <th>12:00-18:00</th>
                <th>18:00-00:00</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th class="table-side-header header-2">Congestion Level : Road 1 (R1)</th>
                <td id="c_r1_t1">--</td>
                <td id="c_r1_t2">--</td>
                <td id="c_r1_t3">--</td>
                <td id="c_r1_t4">--</td>
            </tr>
            <tr>
                <th class="table-side-header header-2">Congestion Level : Road 2 (R2)</th>
                <td id="c_r2_t1">--</td>
                <td id="c_r2_t2">--</td>
                <td id="c_r2_t3">--</td>
                <td id="c_r2_t4">--</td>
            </tr>
            </tbody>
        </table>
        <hr>
        <!-- Traffic Color Light Status -->
        <h4>Traffic Color Light Status</h4>
        <h5>Traffic Lights will only work when the Iot Simulators(SE application) are running</h5>
        <div class="row">
            <div class="col-6" style="text-align: center; margin-top: 8px">
                <table class="traffic-intersection-table">
                    <thead>
                    <tr>
                        <th colspan="3">Traffic Light 1 (T1)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <div id="tli_1_red" class="status-round"></div>
                        </td>
                        <td>
                            <div id="tli_1_yellow" class="status-round"></div>
                        </td>
                        <td>
                            <div id="tli_1_green" class="status-round"></div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-6" style="text-align: center; margin-left: 80px; margin-top: 8px">
                <table class="traffic-intersection-table">
                    <thead>
                    <tr>
                        <th colspan="3">Traffic Light 2 (T2)</th>
                    </tr>

                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <div id="tli_2_red" class="status-round"></div>
                        </td>
                        <td>
                            <div id="tli_2_yellow" class="status-round"></div>
                        </td>
                        <td>
                            <div id="tli_2_green" class="status-round"></div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="image-container">
        <h4>Road View</h4>
        <span style="font-size: 0.8rem;">Red Triangle Indicates Traffic Light junctions.</span>
        <br>
        <img src="assets/img/road.png" class="route-img" alt="Route View">
    </div>
    <hr>
    <!-- Route Details -->
    <h4>Road Details</h4>
    <!-- Road Details Table -->
    <table>
        <thead>
        <tr>
            <th class="table-side-header header-1">Road Details</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="table-side-header header-2">Traffic Light (T1)</td>
            <td>7.429981483737187, 80.44820444218345</td>
        </tr>
        <tr>
            <td class="table-side-header header-2">Traffic Light (T2)</td>
            <td>7.430321923688058, 80.4484082900562</td>
        </tr>
        <tr>
            <td class="table-side-header header-2">Road Start Point (R1)</td>
            <td>7.429853818687599,80.44800729983284</td>
        </tr>
        <tr>
            <td class="table-side-header header-2">Road End Point (R1)</td>
            <td>7.430107818906, 80.44877307046004</td>
        </tr>
        <tr>
            <td class="table-side-header header-2">Road Start Point (R2)</td>
            <td>7.429857808220969, 80.44813336364888</td>
        </tr>
        <tr>
            <td class="table-side-header header-2">Road End Point (R2)</td>
            <td>7.430621138270807, 80.44856385816959</td>
        </tr>
        </tbody>
    </table>

</div>

<script src="assets/js/script.js"></script>
</body>
</html>
