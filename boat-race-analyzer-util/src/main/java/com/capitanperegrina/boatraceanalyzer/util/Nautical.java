package com.capitanperegrina.boatraceanalyzer.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Nautical {

    public static final Map<String, Integer> typesByName = new HashMap<>(5);

    public static final Integer TIPO_BALIZA_BABOR = 1;

    public static final Integer TIPO_BALIZA_ESTRIBOR = 2;

    public static final Integer TIPO_BALIZA_LATERAL_BABOR = 3;

    public static final Integer TIPO_BALIZA_LATERAL_ESTRIBOR = 4;

    public static final Integer TIPO_BALIZA_CARDINAL_N = 5;

    public static final Integer TIPO_BALIZA_CARDINAL_W = 6;

    public static final Integer TIPO_BALIZA_CARDINAL_E = 7;

    public static final Integer TIPO_BALIZA_CARDINAL_S = 8;

    public static final Integer TIPO_BALIZA_ESPECIAL = 9;

    public static final Integer TIPO_BALIZA_BOYA_ROJA = 10;

    public static final Integer TIPO_BALIZA_BOYA_NARANJA = 11;

    public static final Integer TIPO_BALIZA_BOYA_AMARILLA = 12;

    public static final Integer TIPO_BALIZA_BOYA_VERDE = 13;

    public static final Integer TIPO_BALIZA_BOYA_AZUL = 14;

    public static final Integer TIPO_BALIZA_BARCO_COMITE = 15;

    public static final Integer TIPO_BALIZA_PELIGRO = 16;

    public static final Integer TIPO_BARCO_BLANCO = 1000;

    public static final Integer TIPO_PUNTO_NEGRO = 1001;

    public static final Integer TIPO_PUNTO_VERDE = 1002;

    public static final Integer TIPO_PUNTO_AMARILLO = 1003;

    public static final Integer TIPO_PUNTO_NARANJA = 1004;

    public static final Integer TIPO_PUNTO_ROJO = 1005;

    public static final double MILE_IN_METERS = 1609.344;

    public static final double NAUTICAL_MILE_IN_METERS = 1852;

    private static final Map<Integer, String> iconosHtmlString = new HashMap<>(16);

    static {
        typesByName.put("Pin", TIPO_BALIZA_BOYA_NARANJA);
        typesByName.put("CR", TIPO_BALIZA_BARCO_COMITE);
        typesByName.put("Morrazán", TIPO_BALIZA_BABOR);
        typesByName.put("Desmarque", TIPO_BALIZA_BOYA_NARANJA);
        typesByName.put("B.Llegada", TIPO_BALIZA_BOYA_NARANJA);
        typesByName.put("CR Llegada", TIPO_BALIZA_BARCO_COMITE);

        iconosHtmlString.put(TIPO_BALIZA_BABOR,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsSAAALEgHS3X78AAADtUlEQVRIib2Uz2tcVRTHP+feO29+vJlMJpMfzTSSqklrEqkLLXUrBf8CEQShZiVYELpwKQouRRDdSEVCNq5dadNVW6iIUhtrNVWkSdOKM5IfnZk3P9+8e13MJKZ2oumiHng83j33fD/3e8/jiHOORxnqkar/HwCz92NlZYVisYgxhunZoxjzdzrcLhdsNcgBrpNOr6tYLBARAKIo4vbabaIoAmBmZoZDhw4BIHt7MD8/z+LiItlslo/PfUQ2m91JKXPhwvtHrn0/HyLR25XG2Ylnjn/+WGHcAtTqdT785DNq9ToACwsLnD59+kEHzrndx0aCjbondIDfbPvjnXau6bAbxeJc/sknTNhstAHCZhPnLP1+GPPAys6RnUP1CpxzbFlXuWklaFlH01pSfoqBgYHuXm3Yua4DA4wyGNVNh2Fo37v89eLd9Ttl5/Dq7dbVMy+8aE88fxKA7a0tPji3ALX6AQEC1mhsTHdPaJS88vJLla3tza+ME/ITk7cen5zs2FYbgJ33gR10wpAbV7/F91MAOOfEu/7Da2+UN8+WLI23Ll5+vRqUvzw6PW0BKpUqUSc8OAAELQm0JLtA27G/lWs3U+1WphFaubG6Gn92408pJrrlQa2GjezBAUZrpqanyPaaCHDcSNyc/0KyWpnxkeHm1OycjcViXYdegKiHaHKn0+HWjWv4qdTu2tjvd4cExEdiCWQw7nlk0umuX+cQ6T8U+gJEKfTwEKYnAIi/tTEkiKTEmU5QHf55+bocm5pyANUgwNmHuCLRiuzYKH5m14EkV6JhQGIg2bg3EjqrWy6y4HCug1IP4wCB7YCw2SDhZ3AgiaCe6xVIzphhL5nUcd8PvZgmowStdV/AvtNUrMVDSPo5xCRV0trBXoHktcqhlfbTacJ6C7vP9ezrYCea9RqVX38BnFKVcg5ARBjzYvmlpSUvn8vU8uncv0nsCxBpt8cTjfLU4L3gqVwYHhupB0d3kqcy/tMjieS7I+vrqwy1boV+4kdgje5cPAAgipKz333z6dz2xqm4izzlELdnmD3n6bETUetN+9N1LBItpQbON5rNV4F7/5Tq3wPnVLzZyCecjQtyn/juFhEEwYCubm4eCcNwqJ/UfQ4KhYI/OTk5lEok/C0wd6zbv3u9EBxVpWOFw4XDWulWqVQqAZ2+gNHR0cGJiYmT1trkO2t/XGzX6yWg/wzYE1ar2ujw6PF4Ij5eLpcvA8W+gOXl5dKVK1cuATHgEuD9l3gvHKtrLSACgr2JvwB5Kogbq1ioDAAAAABJRU5ErkJggg==");
        iconosHtmlString.put(TIPO_BALIZA_ESTRIBOR,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsSAAALEgHS3X78AAADoklEQVRIib2Uy28bVRTGv3Pnzssztsd2HoaQ8KgoJO0GFiBVIizIBqkLiMKGTf6AIlal/UsqUbEIQkgg2JKiqi2iQlRQicosUhrRh0nchFpt7LFjx565DxZ23YSMW7IoVxqN5tzR9zvfPfcc0lrjaS72VNX/DwDf/VGr1VAqlcA5x5GZI+B8zza01tSNY5NbZkREg7gQAjdWViCEAABMT0+jWCzuB5RKJczNzSHIBvjmy6+RC3J7AOuN2qtf/HLxnbdHpz6dfWtW7E5sYf591Go1AMDS0hIWFxf3A/pZQkODmxzcerQttTQ/31o9eanIj9//7ty1medfvuI4LgCgVQ+hlELShUmugQZEswPRePT8XqnM/qDDhVbgjt45PHpy+dL5tJVyYKdcWCkHBEqU2ucAAKSSuFldQ6ZTf+iKvr18ocg27lz3tU61YzH+/frVmfl3j/+azWRgE2GIfjIAACzbhmXbAIA4jnX556vLqlK5ToAhGdNRkLsrIEmS0pLUMJlkABHBtB1Y/TO2HBcfnTrlXtmqvEmasXGwlXHDrtaiHV2/30EjDCFVMiQRoKTC1l8biP3mIHbj5h/2uUPW6TDvTo58tnx2sk3X5hc+aAJAq7UNKeRBHADcdGBaziCW87Khbcq2Jkbxdjt+pnhI+E6qtykU6CA10Bpo6QhMRYNYMFG0lFNNgREpz3EnX5zSQvda4eH7AA4IaddBJuUOYiruOJEBTxMjZP28jCKedvsORQwaYmHoNS1vbsBrhINYXcWp7hQcTQDlsyMrq6vWa8eO9eDdKElmOIARYczPYPSlKSjV606908gJfs8CACPrB4yarssdMEbomh0MK0JyDaTCZrmCOgmMZUfgOjYanXZOur3OV56bNrmZ8iwHG9UqWu3WsD57zLgmgDMGQyg0whAb4VZe9//vera39vddb3XtFrrx8ON5PKDvcCeOPc35c520cxiMGADowPe6Dj/ajKIJIaUPgB+oyBrAJhdv/Oi2Pn7gdqdDk4rbhs5pGAQAnULGr55478xZiptpET54tiVK6ULhkzAMq/8JoKBRtuTrv43ZH+qEMaYYUSdlZTpApgZMVKPtF1Df+grA+X5+yQDbtikIAs45h4xig4RI0t+3SCjDMIwgCALebrfFbsi/AWYhn39FCGFXLlyu+T8ZF7XW/pMATOrYMcyCnwmOCiH+BLCdCGg0GvGt27fLWmsLwDqAEgDjiRYAhEB4rye8Z278A0gWlF8mlFCiAAAAAElFTkSuQmCC");
        iconosHtmlString.put(TIPO_BALIZA_LATERAL_BABOR,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsSAAALEgHS3X78AAADuUlEQVRIib2U32scVRTHv/femdndmcnM7Cbk16YVY0PR9kFaf4HBkDcJ+KJ/gZqH4oNQxL63PjV/gUUQF4oWX/omeSiFSrGUVDDSkkjamkRMJmmym83OzuzszL3Hh8Qka3dD8lDPcB/mMHM+fL/nnsOICC8y+Aut/n8AtIMvc3NzmJqaQs408dkXF2FaVsvHREp7+sdCMQuxPNDfv+dtGIb4+ptrCMMQADA5OYnR0dHnAb7vo1QqwfM8fPTBh8jn8y2AJ9sbZy/fn7488tvyhS8/v7gqhAAAVLYquPHDDVS2KgCA8fHxPUBHi7jGWo5ilPlubf7S/Mv5iUc8/vSvJ4vCNSy4hgVHt8DYESxqJbCdsxsP/KXxu83NCc7BN88NT37/082bTrf3yDAMbFWrUEodHcAUQaxVoTV2AFJKTH97fYVmH1w3pCzEgLxdrb907szZubH3xpTQBTpJaAtQIGyrJriKAQCplMho2vyZ3uJXBOLZbJY81w2KxSKZpok4jsGOA2gmCW7N/ALTNPdyJ0ZecVbefe1qZOrdzvr2zKvbmBoeGKJgo4x6tQo6jkUAoCSHkvt3IKxt1xZE5cSz/qHxpR/vpuUavJGBYR8AakENUh4DYOg6xt5+B57r7uWSJKF75d/FOgNLMpocGRqpV5kEAARMgdB+5XSwKMXPszMwzf1Bk0rqzwbRRciBF5z846eP9dffOA9g50p3irYAzhkGXBe2be9DpcySEdgAg3BtTyDI9pjOjmIJcNZ+pNpfU85h9XTDsruAXela3MiOrixakQaEYcNpxJEZbPoAgCAIjjcHSilsVKtQKcG2sgAD9HqYuXrn15ybprgXxdYVqVnKzEAIARBBdehBh1VBkEpBEwbACER1sEbZy0qVY0To0bTc+qrv5XMZpJKh2VQd5+DQdS2lxN9LawhXI6SbdU8oKRgAm8FgShYeLiyzIIqBzj0+ZBcBPFbS22Lp4BrHSWXx9/VTQ4IDCBQZ7lBx4s8MqI8aKxZnvmXbK/UgSI8GIIbVKHyz1CtLlTzrizXVJZnLr51+i+39R2pylvCJrpKG15Dr1mDfx/D9O0BrM9paxADEUdPzc/xU3RBeyrkgxhjhwMMYFGc81rhZEVSs1YPz7eq1KDAMQysUCgXOOd/YWHONMk/4If7+G3qcKJ0Lt1Ao9EZRtA5AdgIIx3FOxnHsPLx1u6lPJyUmk/xzFf+rmEANhdC2rNNpmkYAttoCgiBoLi4uzhGRsevU/XayO0RaKZebAKKDyX8AlXOifw4cRxsAAAAASUVORK5CYII=");
        iconosHtmlString.put(TIPO_BALIZA_LATERAL_ESTRIBOR,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsSAAALEgHS3X78AAADw0lEQVRIib2WXW8bRRSG37Mzu/ayXttrp26chjq40DSorakqET6qtFKvIy6o+BcI8QOQkBBc8gMQJVwjQAhQBRK5qvisQEJKIFGgaUKo0y7Ysdd4vfbOHC5shwacKL0o52Znz8x5H513Z0dDzIwHGcYDVf8/AHI4YGZ0u10MLZNC7FlIgAlmAwCYqAcife98HMe7Y8MwYFnWXkC9Xsf8/DxqtRqSloXnLl1AcrAIgDgTNF+bidoXYwa/C/N1OTFxLeO6DACdTgfvf/wpOp0OAGB2dhYLCwsgon8ASimsra3B933YCQvrj4zDTvQBDNB0rXEy3w2fihj6y1u3LxbOP/HFw5MTEQCEYQerqysIwz6gWCz+16K9QTCEgCHkEIA2oGoaqsealdJWEoKcQTlBgEAHf4N7Q5gmypVZpFKpYXfqzatX33mlWm0xIO5G0fKTUyf05PRJAEAQtCCkGCU1GiClicr5Z5HNegCAWCn+9uvvvtnOjf3MpHHG8+oXnpntlUolAEC9vgMpR5sxMqviHm4u3YA76IAZ8gXPeXVGpy/tgKIPc0dfTqS9rx7KFQAAHUgQjd7xIwEMICQJQXLQQU+tbGxuXe4Gp2/HvPP24vXi2MzjpIkYABqNBpRShweAGXG7gZjiwSvz9LFxbawH5BokCvncjmsL9n9fBwAEQQCt9Uip0QDDQDKVRdJ1hxkqOG4OROQSLEfKtCQTutf/KYfPQwOEEJg6NYVMNrsLsFd/zAOAQ5Am63yzEyDv9DcBLKBv7CEBcRThxifX4Ng2BqVGubo15mQdhJqF/k0dCdttI1OeUgBgSROGcR/b1BAC5elzMMwkGAww6K1HC9mlLIGUJrldH1vfvCtYugrMyCRGi+8LYNZotZpwCxacjAettah1/GzDJBiSkUnZOdtMSEeY3XTKButoX8C+xzURIEkhbO5g6+ZGsqXjNAAwEZHn5imMZHHMQ9zt7it+IAAAaa3MTrftJYV+LEHazfZ6yPR6yB/xij8t/VAO/2rlWHPiIJ2RFhW93NHSrysvHVsOzqVbQdmMIu9yUuSZ+gea6MYV6Zqf82cfBUHCrm54ueus1BsAWocCjKed409vrr3oKJXaTe51QgIooNUsoNU8Ef7pl3Xc+wDA9wcCpJQkpQTIMO7nMqA0WyBKSSmJ/1W4C7Bt27jy/JXpO3fuZB3i0nu3fqnKWOUOA6iS0Tp7tnJq8nipW92urgPYHs7REOj7PiqVSt73/QIAk7U+DSB/yCZiMoxlAH8wc2Nubq66uLioiQh/A1J4jqbQGIrfAAAAAElFTkSuQmCC");
        iconosHtmlString.put(TIPO_BALIZA_CARDINAL_N,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsSAAALEgHS3X78AAAEIUlEQVRIiZWUy28bVRTGvzsPzzjjV+o0rvMqTUqQAnWq0CIUFkQqlA1CYkWFhFARC1pYBNQNfwUUqS0bFkhJxAqSiA2iqCBIAwpSqihKUwdbSUnUuHl5xh4/xnPvYWFiOe4YkiPdzTn33t/9zvlmGBGhPlKpFNbW1jCYSEBV1QM1zrnyVyoldXV1Obqu1/Ku62JhYQGapmF4eBiSJNVqChpibGwMX1y/jonxCbRGIgdqc3Nzr9/88lbPpbcu3RoZebmyn89ms3j38mXEYjHMz8+jHi7BMxgMw0AgEKwtxtixqcnJT51S+dq3k98lsqbJVJ8Pqs8HxecDGjrRVAEAEAiFQgGaptWIU9PTb6bS6VNEpO7u7Lw/Pj5x7bWLF20AyOVycDk/PAAAJEWGpMgAAM45zf7x+w+q5gvlcvmWfD43s3R/CapSPV4ql1BxKp73eCsgQqlQQNHnq+U+Gf04e/PGjVeTyWSov//pr698cMXe77Vpmvjx9m3vhzZTwLmoX2xxcfGNlWRyhIQ4l06n315dXVU74nF0xOM4EYsdcM7/KnBdF8mVFQQCAQCAEAJT01MZ085/RkQKEeXv/PKzdu6F8xUAyBVsNNr9PwEAwAUHF9XBERGdSSTu9Pb1/QoA4XAYQ0NDFdd1/1XLjwZgjCEQCCAYDNZyZwfPCtu2HQCInYihu7u7VlNVFYyxwwNkWUZ3dzcidR+aUy5jY30DANDT0wPDMGq1SqVyNAARIZvNHpBdLBZDG+vrg4JI2tndWe47fTqz/wDbPuIMOOfIbGZg5+1a7t69+dbZmbtfVRwnur23N3r1w6sTAwMDHAAsy4IQwhPg6S0GQGYMSt3qPflUzqeqgguh+/3+YmskIizThGWayFnW0RQQgKJThuzU/U0VWXdd1wAJpVAshDbW11lHvIMAwLbzIHFEm5IQoDrZTqmsu67bQgSZgbUXi0XGpOpgJcaqsj2iSYsYDM1AOBCCofth6H4wohAXQicipmm+44FQSG6NRhFta8czz74ERfF5XeUNICLYVgnmtg0hAE3XwYWICM4VxhgjQccghFIulpDdzqLteA8kST48AKjOgQTgusDuzi6yphUVQkioNiM6O3NX2Xq8C97EPfvRdAYAIAiy6xYi4Jm2UMvD5xOJssQAlB21R5Hy57ceJ9Na58De5qO/Lc65J8kTIITA1taGeuHC0udnnlt5JRw2Yy16yZBlIQEA5yxRdjLf24V0fn3jp4fvvFcetay93+Lx9ies1EQBwbJNOd65/OKpk5l+NBxTFWK65ujhoKNLUjnkOPIAEc0AjTs9APF4XOvr7YtCYv65P5XM5qb/kfcjqmFasttiBP2dHawNwFYj5AlAJpOp3H+wnL3/YDk38Y34iDFqQ1OXV0OIQgZgdjAUYo2AfwC05P00Y4ZDogAAAABJRU5ErkJggg==");
        iconosHtmlString.put(TIPO_BALIZA_CARDINAL_W,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsSAAALEgHS3X78AAAEEUlEQVRIiZWUz08cZRjHv+/OzDszu7M/YHeWDWDV/rLlQkJiYxCDJ7zowYSLSVFKIinq3+DBmyZ60oMeOBjtwYvGSCRFYuPBxgpsYksNUFIpIEJ3F3bXnd/v+3ooTAudMfRJnkzyvDPPJ9/vvM9DhBA4GuVyGZ2dnZBlOaxVKpWOvd1dEwB0XW+05/PrsiwLAAiCAEtLS1BVFf39/UgkEuF35ChACIHh4WG8OTKCXC4HAGCMYXJy8tW5Gze+EAKKqmtfnu/peX90dLQFAPV6HRMTEyiVSiiXy9A0LeyXQEQIISAYD5MHDNvb29c5F8u+58nLKys7VFGE57jwHBee60Lwx52IBRAQqLoObT+NdBrj4+M1qqmf/GtbVzOZzNQrQ0O2qlKoKoVKKUAi+0cDQABFUaBQGubJk6fEpbGxXyVZ/nji8uXlc+d7hFnsgFnsQL5gHvL90ZAjqwCCwEcQ+Iew1Z2dS2a+oKbT6QXbtsID27YQdVliFQgh4HkeXNcNc/3e+tnpH6ffcyzrndmfZntdxwFnLMy4iFQQBAEWymUYhnEAJHNzc727jXqDM2bcWV19/esrV24OvDjgAUCz2QSLgcRaRCkFpfQAKObn52fvVyo1ACohZHdjYyMhSRIA4OD5RIBEIhH+OEopLo5crO3VdmYBIJcroNRREsCB79H+xwIoVdA/0If29raw5jiW+OH7r+B7Hgrms1D1BAS8B+2J92QAQggyGQPZbPoRRYIQIlQCTgiBB4gjpkeriLZIAMyVETgPj5nL2196ofxpUq+3fz658eHTJ57/udRRFADg2q1YlyIBjHNsrlXQqgdhrdFstAr5enepuNU3M7P1zdhbvb+0ZZIBAEiEg8RMcjQg8HH39m9IG6mwxgWTnjvFkqlUIBfNrMjoeQb/wS2DryBuV0QCJEnGidPnkMtmH0KZrelJnlJVJuk6M22nnqBqiQEAdUXsLooeNBZgcXkRqWQKjPN9VZ46dVVJcd5GqlUUVu6uST7jjCoKJCkBvv/esQCCB6hXFyGxIopdfVCohnqjTmdmFM12DGK5dluh1C11PXMaMnPQbNVBYn5C9AoEoMgCSY1AYrsIAgvVWjXDONMJASEgbQvzC1JgbSOlyrHNYxWESiDg2hYIC6StzdUCIYGsyALZbMpMCCetUcUSAtHe/D9AgIsm0ZKs/8yZO68VzFrP4MvVsxNv36cQBFwofQL2d8C363vVp/64dp185vt+9dgAzjkarXXS1b33xuBg5V3y2BTZKaBxAeTvC5ubt4ampvG743jTiBi3SIBpdqb/2miplYorb/2j+4i9hMDWtkQ0Xevu7lYNAM1jAWzHNm7eWivc/tO79sFHchoCNA7AOZhlC58Q1zQMwwJwaEf9B0VS4Ey5JvWKAAAAAElFTkSuQmCC");
        iconosHtmlString.put(TIPO_BALIZA_CARDINAL_E,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsSAAALEgHS3X78AAAD/UlEQVRIiZWUT0xjRRzHvzPz2r6W/gNeW8oWFxJJXA6bmGAIHHQPiB7kxG7Uk0H05k3PXo0n1KOaeMGT61EuuwYTuUji1i3Q7LK0BQKFLSCl7etr35s34wH7tsAru/tNfof5Teb3+f1mfr8hUkq0K5fLoVAoYGhoCIyxc3uQ0qf6/ZwxZre7OefIZrPw+XyYmJgApdTZU3BBCwsLmJ+fx3fffItwONwend6/d++znd29zOzs7P1oJOJkVi6XMffpJ0gkEkin01BV1TlF4SJCCILBLoTDIcd0vT6yvLz8+ebGxpd37/7SW6/raDQMNBoGms0GhBBuodwBUkoIALaULWNLfyx9oOt1v2VZI+n0P9O7xSJlHg9a1kmuAADQdd2x/f19eXBwcCKk3LGFOGg0G4OLi4sezjla1kmX3qB1RTFNQzQaBQBEIxFZq9W2iJTDAHBSLptCCOZVzjL3KB6QlwEIIVB6WkLDaLTWUovFfi/uFZcMoz5gWdbK1OTbDUrOwlICoAOiYwVdXV0IBoOO7/07d07X19a+Ojk9fWNu9uOVqclJQf4HBPx+UOZ+264AAOCmCW6azjqg+uX0e9N/bW5urt6emam0z0h7378QgFKKxLV+dHd3Oz7TNDE+MW69devWqUf1oVyrOnuneg0XB/a5FRBC0LoCZ00pLG5BSnkuYKfgHQG2baOQz+O4bZJ1vZ5aX1ubIYQgFAn/qmnabiAQAABUq9WOreoK4Jwj/fcDtAIAQL6Q966vrn1hcR4oHR/l3516pzgwMCAAoG4YsF8GwBjDayM3EAqFHF8oGjEeP3osOedMVVV7fGxMJpNJAEClUsHPHabZFSClRPmkDG49y4pRqkpb+IUQXoUxdWt7G7t7e2hVwC3rxQEEBD5vAKq3C60Bahh68JXrhsptS3mSo73JZBzJvsRZBdUqmOLeLx3bNBmPw6cwWE0dANCTMrvnfip5pbTY7Q+jCUY56++L8nKlgq2dQ0jp/pt2aFMJm9jY3NmC31tHTIuBKqWesN+igE2iYRHbL+6wnp5efvRvHUx5VumlZN0BZ7IFcHAENMwkAoHeKKWCUAZomuzx+ftYtXb1T3pFBY58ul7tfvDwz+TgwNZoKOynkgiiKIFXM6sPx0d9o9uU0mOqsDIA12lzBxACIYW6srLyfT6Xe9MyTU0Iof7wo0YACSEwRsij3wq5nB4MhTaa3PrIMIyNFwZIKWEYhnJYKr1u1OvXL9ABgEgpfM1m02da5s2nh4c3bdt+4lbFJUAqlfIPDg6mMplMoG4Yx7YQJ+j0ggCIJM14PH6tV9P6Aew9F1AsFs1sNlvJZrOmEOJrSNy4CgACQQjJAJDDw8MKgHOv/h88xetZWxja/AAAAABJRU5ErkJggg==");
        iconosHtmlString.put(TIPO_BALIZA_CARDINAL_S,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsSAAALEgHS3X78AAAEF0lEQVRIiZVUS2wbVRQ97z2PZzx27PEvcWJHcWOlqKqgicoKKUhISCwQ2aREYscCBVZZFLFCIntCJXYt7FiVIFUCISEIG5SwQCVKQC00BMU0aQC749+MPTO25/NYpHGaMI6Su5q57+qce+6PcM5x0jY3N6EoChhjAADP8wLlUklxXReSJPGxfF6TZdk5jLdtG+vr6xgbG0OhUDiGRU4ScM4xOzuLubk5xGIxAICqqkMff3TjpmVZgyFZVpVk4r13r1/fGRkZ4QBQr9fx+twcFhYWsLi4eAwv8L/0n5gYECAJQQBAs6HVDNPcd2x7pmm0fvqzuBOuVWuIxxQAQNu04HmeL44vAaUU6cwQEonEAUC3Y7uc36KMvVBTG989f/XqTsto8Xu/3T9IoNmE67pnJwAAxlivB13bTjKCF3XD/D2VTLLxQoGqlQoIIQAAwzDOp8DzPFTKj+F0ugCA9bs/24TQa7IovuQBd1dWVpaXPlzS0+k0AEDTNAQC/rnSfgqetqnJyWZQEm+BkGqlVl2fvHJFzeWyCIUkhEISJEnsqTkTASEEQUmEGJIghiRkR3N8enr6W91o3aCULs/Pv11NpweRTKaQTKYQjydAqX+ufXsQEAQEBKH3/9rMjPFYVW/mstlOoTDOOT+q+dPfZyJwXQ+PHu5Dq+k9X0vf529ce1nf393G13c+OxZvmBY6bescCjiHZVoQ2JGCSnkP2/f/ACECXCSOhZtWG34XoT8BOLhng3vdQwcJip28oljpru2a1YbxIBhk7lF02x8G/aaIEIhCCKIgQxRkCExCQPj31bfmf/w+mtxc+uqbH9KRaAhKIgIlEUFMCYNQ/ynqswcuqpUiOu0wAMBxHL7398OyLNlyq+XFQGR2+dIrEJ4MgaZrCLAvz05AKcPg0AVEo9Geb+JiJcLYBokrlnT5mUu8XtnqvTVbBjzP8YPqo8B1Uf7nLxhauOcbHSvFQTjNZHi0rDZlkDAYO6gwYwTos2j+ChjFUG4ICSV5uKFEUTYTADCYomFdb8YyqQkEgwIgeKjWq6CUnUOBx/Go1IRasSFRDg6XPPucmiLgJDLgSIAV2939lTQtl2fyebQ7/ce07y1yXQfVuoqu18b4hVGaiCMOEEQHeDA8EIzFhydg2jZc17/2pyoADu4RCCHVeku6t72bCEW14ZLK0DQgcG7l9/aLKdtxmgDpAPBP/zSCdrs98svGxvtaozFpWubwF8tmlrERcA7BMIUPPr99+52gKJZL5fKyElc+AeArpS9Bp9MZLhaLb3quKx95DyvqKpqmKQAurq2t0kg0egdA6cwEU1NTMcd2YgC6nHPBL+bIiJzNZjO6rtcAdE+++hJsPdgKra6ttnRd/5RznjsNnlJabejagGEYUQBVnOjHfzuIw7gdogaMAAAAAElFTkSuQmCC");
        iconosHtmlString.put(TIPO_BALIZA_ESPECIAL,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABEAAAAYCAYAAAAcYhYyAAAACXBIWXMAAAsSAAALEgHS3X78AAADUklEQVQ4jY2UzU9cVRjGn3PuOTPM/aLzzYCURgglTUmtjdI0DhgbY9RFg93VhupGWdRomrgwadKNceXCrf+A6YLGBMGNi1IoTfwIpUrM0ISKFsaBAWaYzzt37r2vG20Kc6mc5Zvn/PK87/OewxzHgW3bePowxiCEYIwROBdERAwAEVGLFgDE/Pw8xsfHQURPipFwO7v20dtpq7mWyG0enXrxBe2t+4ub9/Jb7sbkd1Pked5eSKVSQSaT2QMxdE2Uq+V3z7+29s730/ELpllIT07qN35Z2LlVq1UrT2sBANPT02CMtVg82h0+NfNDbMUqSu+zT5OzQigfA+hsEQLgfkVdU/H+e4OnDbOh3ZlTt6+MVc68ci5mANj10wu/YiCgIJut/3X9RuDbhYV6YPxD40xuoxEGEAFQA0D/CwGA27fXf1z5YwdE5H3+ReOW67rJg7QHQl5ND9WA+/cGTw24W1s7Sj6/3ZZMxitzcz+1puNLFgqGh0/T2KUPrGhHCMnORLMtxK2lpWWk0xdxKIgiFCQ6jiAoQ6iULCiijnBHEBXLxv50n9kOABBIgoiR57me57kt+/EsCAMHec0jMvTlV+Fo/tg3NyNf//p7YOKN119u7gvmYIjr2rg7O1sdGV7S+ntz56amgnOLD6xUd0x7TD52fJdNCBUnT1zgqVRUVYQrDEPpZ5wHk/F+Ymjdbl+I4zgQzOaG7hqCE4vFpAYCrzVsn2YOgBBZqNbvCs7zpsKBdtPWidzQbmGR+c3EFyKlib7ey8GQqpgMhESSdMZFKJ54CYdvp1nH+todTWFVlRghlSSdPEdbWXnADt8OHJDM6oI3giCGeFyqHpGxnrO5n5M9EUfC7VzXuDkyciIaiwaHhKQ2MIaulKufHTIHBwb05cxDY7NY8grF4q775OJ/n5JpGsrEzfPX/8x0/lb428xXt6XllDi5JU6NAveKOdkoZM3txw/jy9c+6RsDIFucCCFY3/NbZ7s7cycJ+x6YAmaoXgDkREy9HLHt8psA5gE8AkCiq6tLjI6ORqQUMrf5CMGAtMlzfWcFBhADSmW9p6/3ueM9x3qKMzMzO4KIZDab7SkUCtrVn6sT9XqwZjcavh8Q/TvTkCqKui67V1dXN4io9g+lBncrCDtAmwAAAABJRU5ErkJggg==");
        iconosHtmlString.put(TIPO_BALIZA_BOYA_ROJA,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAcCAYAAAB75n/uAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB90DCAwXFPYjXuEAAAFbSURBVEjH7ZXNSsNAFIW/pGlrJeLfRl0oKqIP0HUXfXAX2egiLyCCIIiulBQLMbFm3JyBNNhk2tpdLlwSkpnzTc69M4E22mhj4+H90xyzLsCO83XvVcQNUPwFCxzFPaCjDATyJVoAM+BHOQfxHMR9oDMdDbPqyzCKr4EcSJVfghQW4q8qDjAdDR+Ac+AY2Ae29ZXWyloAgL9IvAS5BS6BI2AX2CrrBk2rd2yCK11z2ZTb+jRZ1HUEnMqmPWCgeZ5rDVziUOI7QH+ZGrjuk55yrsAuABNG8U3dgDCK7+V5Xm3RJoDdnVkYxeMF4nfAB/AOJMAnkJUhXoP/XbXeCXChbjkDDmRJLsAz8Ag8Aa/ABPgGiqBm9UafnEqkZ1tfBbWABHgDXjQu1TwDmMDFIq2I0opD7aGZgImeT1wtqloVqP0Gyn7psMtKZ1EmqDNgldPULHOarv0/2Pgf7RfDVXbRbqkVNgAAAABJRU5ErkJggg==");
        iconosHtmlString.put(TIPO_BALIZA_BOYA_NARANJA,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB90DCAwYDGLX2ngAAAEySURBVEjH7dPLSgNBEIXhby5eUbxt1IWiIvocPlgWeTC3+goiCILoSokoxIwm46YGYtCkve1SUMww033+6lPVTGMa0/j3yP5oT/1bQLMuj/dsRLzG4DNYmSieoYgsA5SH6ABv6Ed+gGQJ4jmKfltv9GfRcoQK3ciXgAwaSP5Tcei3XWAPW1jDYpyysXIsAPKvxIcgpzjAJlYwP6xbTqo+cQgO41mFTVXTn0kWzSQCdsKmVSzEviy1BymxEeLLmPtOD1LvyWzkhwanAOqi5XjcgqLlPDyvRkd0EqC5nb2i5eQL8TM84B4dPKE3DMkm+D8To7eN/ZiWXayHJVUArnGJK9ziEa8YlGOqr+PI3RCZjX/P0dAG0MEdbmJdN/bVqMsUi6IiQxUvxR16C2Anvj+OWvQO/EBdzT2JTeoAAAAASUVORK5CYII=");
        iconosHtmlString.put(TIPO_BALIZA_BOYA_AMARILLA,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAcCAYAAAB75n/uAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB90DCAwXLjAvh1MAAAFOSURBVEjH7ZXBToNAEIY/FrTWaES96EVjjNHn8Bl8Xq/6Cl6NiScJjSYIdlkv/yZbYmHb2huTTCDA/N8yMzsLo4022tYt+acYtynAf2d0n3TEHdD+BcsixRMglWcCGYm2wByw8gVIEiFugLQt07r70uT2FmiASv4tSOshZl1xgLZMX4Ar4Bw4Bvb1lz6VvQAAs0w8gDwC18AZcATshbrZ0Oojm+BG10Zpanx9hlK0Ewm4UJpyYKq4JLYGMXYq8UNgskoNYvfJrnyhwDEAZ3J719sFuX1Wzptuiw4B/O6sTW7vl4g/AQXwAZTAJ1CHkGwA8KMNVJrcPqhbLoETpaQR4BV4F6RSXC/AzxergEKCAF8qqAeUEn/Td5XiHOCymBQBs6DPC+BAi5sLWOr5rJui2FmUqf2m8kkw7OpgFtWCRgPWmaZulWm68Xmw9RPtF338ewW6evo6AAAAAElFTkSuQmCC");
        iconosHtmlString.put(TIPO_BALIZA_BOYA_VERDE,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAcCAYAAAB75n/uAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB90DCAwWOjPuYm8AAAFZSURBVEjH7ZXNSsNAFIW/pNPWiuLfRl0oKqLP4VPkXbLuu/Qp3OoriCAIoislRSEm1oyLntA02mTa2l0uXBIyM+ebnDs/0EQTTaw8vH8aY5cF5P18vXslcQtkf8GMo7gHtJRGIF+iGTACvpVTEM9B3AdaDPrJr9YgvABSIFZ+CpLlEH9hcYBB/w44AQ6AHWBdf5lbWQkYt88Sn0CugTNgH9gC1oq6pnb2bnGuZyqb0rw+dRa1HQFHsmkb6Gmc51oDl9iT+CbQnacGrvuko5wqsAvAEoSXlT2C8Faep+UlWgfId2dCEF7NEL8B3oBXIALegaQI8Wr8b2vpHQKnWi3HwK4sSQV4BO6BB+AZGAJfQGYqZm/1y7FEOmr7UEFzQAS8AE/qF2ucBaxxsmg8Iwoz3tAeGgkY6fvQ1aKyVUbLr6fsFg67pHAWJYI6AxY5Te08p+nS98HKb7Qfxl1x0XVD90EAAAAASUVORK5CYII=");
        iconosHtmlString.put(TIPO_BALIZA_BOYA_AZUL,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB90DCAwWFZg/XzYAAAE+SURBVEjH7ZNNSsRAEIW/zmTGHxQd3agLRUX0HM4NcpI5Sk4yN4hbvYIIgiC6UjIoxMQx7eY1ZIKTtH+7FBQJSff7ql9VQxdddPHvYf5oj/0twK0L9G5q4hYov4KFnuIG6ClDgQKJlsAM+FDOQYyHeAD0RuNJXv+ZxNEpUACZ8k2Q0kGCn4oDjMaTa+AQ2AWGwKpO6axsBAAEi8QrkAvgGNgBNoDlqm7YVr3nEJzoWcimwvWnzaK+J2BfNm0CK9pnfHvgE9sSXweWvtMD33syUM412Adgkzg6a1qQxNGVPC/qI9oGcLczT+LofIH4JfAMPAEp8ALkVYhp8b+v0dsDjjQtB8CWLCkEuANugFvgAZgC70AZNlRvdeRMIgP9e1VDHSAFHoF7rcu0zwI29LFIFVGpeE13aCZgqu/TukWfPK5izUYIZeEAAAAASUVORK5CYII=");
        iconosHtmlString.put(TIPO_BALIZA_BARCO_COMITE,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAAXNSR0IArs4c6QAAAAZiS0dEAPQAPgA0S7ZxzQAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB90DCAwLLU9Ri7QAAAFvSURBVEjH7ZRNTsNADIU/p9M0iH9YUAFCLGHFikOwRlyF43AU7sIeCRAtJCWJ2byRrIr/sqwlKyNn5j372TOwtKUtzf5hv391IP0S3IAixPrvSIpfgBfu3gFDoNR3IAxbpIL5zCud64AZ0IQK/K8SFcoYYF3rFnhRrJFc/TxJ+ok0kqJUbAMYiSCFCpvQF/9sKuwjcHevP83A7BR4AqYiaSNJmtN53ofuPhHQGbDr7rcB/BIYq8Lcgy4k6ilkmqVIWXN3vxfQObAHbAfwa+BA0uWGv4W1A30CzN3bb3pxDKy7+02I7UiSDpjIZ/o30LozZTwEVoA1d79b6GkwO1E/pkBdhDEcAKXkWMSOJOcmMCrCaPWagM7MLhYkGANbQJUC8EwXJwGVmV0Bh8C+NpdhOvLz0KsPtfwFWJPcJZBS7rY25Pn1UFmjcqsAbIGgk7fCeAZelXAbCTxc+XzgFXgAVnV748NmIZmM8aYqHuX1O8BgfoP30s8rAAAAAElFTkSuQmCC");
        iconosHtmlString.put(TIPO_BALIZA_PELIGRO,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAYCAYAAAAlBadpAAAACXBIWXMAAAsSAAALEgHS3X78AAADB0lEQVQ4jY2TTW8bVRSG33NnxuMZx4m/mtiuHWpSIkoUmqQogqogoUIrpaUpaukW2DRZVyyRsoWiCvErWFGByqoNC6gqo4ACShvhsoHajePPTpzUjufj3suCChiwOhzp6mzeR8/VOTq0vr6ORqOBf5au68gdzEW44COpZLIWCoUEAFiWhV9Kpb+Di4uLICLfS4+NGRfOv/3JaydevfP+u++98eUX12mt+D0+vXbNl1OllJBS+syjo6PPb967t+y67tDW1sMP13/+6YdjLx3rVCoVX5ZhQLXa7TaIqgA827Y1APFzb53D8VeO+3LqILjZbFallCuC84uPLOubdy5dahWeOYRKueLLDTRn0mlz+sgLZ5PxxNz01FR/dna2V2/UsbNjBZtzB3MT5XL5vOu6kW6vu/zx1as3JicnW7VaLdhc+vV+FUSbEujbttPNZrOR5aUlnFlYCDZ3Op2m53kfeJ53mnO+8ebJk/WJQwWUf38QDGczGbF8eenOxt27P87NzbpnF854Qoj/rHQgrGkaJg4fFvnx8f18Po+9bhcA8PhJfyoMEBgRdF0HAAgh/uz/x2zbtlEsFi/bth1PZzLX87nchqZpqG1Xg+FavebdvHXz9V63d2pnd7eysrKyOZ7Pc10PB8OGYXA9pCv2fl9RNZVtb1ellAKNpv/6Bu45FospsVg8KoRUFEWNEzFipICIBcN2vx9m3d3YARVkMCQUx2WK7YB5PBgOk9Q/ipmRz7MJ9rKpp0Ihxp6dyMPud4NhEtyISR6JM4Z0SEsV19bU32oN9J5mZowhkUiocWMoSa5rciExFjaS1uNevNV+pALkg/+adqFQKIylDiypqvqi1+sVrtStKJOABWXODdPXt7/9bitimrej0ehne3t7+z7YcZzpVrNxRUpoRIQmAEgAxE1ynKOdTueorutTnPMbADYBQJ2fn48yopTgInW/VHIF5wT69wcBAoEY02dmZp4bHh5ur66u1lXTNFNEdIIpbCgSHfrKdd2RQUN8MhNnZHjkCBdcSilv/QGQCklNzRFfhQAAAABJRU5ErkJggg==");
        iconosHtmlString.put(TIPO_BARCO_BLANCO,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAABsSURBVHja7NXBCgAhCATQ5v8/2tZDS0gsM2zGtuTJg/gyyGBmJTOgAICXG/YEvHnLFeQbQN9cRSTAa698LhCu5gZYhAb6OmWKRyCePgIMQgGjGnaKAxzgL0DaQ0tfFUuX3SimAEs+nDdRBRgACybM0fs+o2oAAAAASUVORK5CYII=");
        iconosHtmlString.put(TIPO_PUNTO_NEGRO,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAABmJLR0QAAAAAAAD5Q7t/AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AIEDQ0AkkSYCwAAAB1pVFh0Q29tbWVudAAAAAAAQ3JlYXRlZCB3aXRoIEdJTVBkLmUHAAAAOklEQVQY02NkQID/DKiAEU4wMDD8//8fVZ6RESLPiCwJFWRA5jOh6cBgMzEQAHAFyG5AZhPlSLzeBAB01hcLO92PtQAAAABJRU5ErkJggg==");
        iconosHtmlString.put(TIPO_PUNTO_VERDE,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AIFCRQNyECCFwAAAEBJREFUGNNjZIACqfkM/xmQwLNEBkYGZEl0ANeALCk1n+E/Op8JZor0AoSJyGwmBgIAruBpAsKNyGyCjmQk5E0AjdhJmVMIOlIAAAAASUVORK5CYII=");
        iconosHtmlString.put(TIPO_PUNTO_AMARILLO,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AIFCRUx/jTP0QAAAEBJREFUGNNjZICCO+cZ/jMgARVDBkYGZEl0ANeALHnnPMN/dD4TzJS7FxAmIrOZGAgAuAJlA4QbkdkEHclIyJsA1G9RKXCCqp0AAAAASUVORK5CYII=");
        iconosHtmlString.put(TIPO_PUNTO_NARANJA,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AIFCRYSd37tYAAAAEBJREFUGNNjZICCh/MZ/jMgAflEBkYGZEl0ANeALPlwPsN/dD4TzJRHCxAmIrOZGAgAuAK5BIQbkdkEHclIyJsA5a5P0QqdM1kAAAAASUVORK5CYII=");
        iconosHtmlString.put(TIPO_PUNTO_ROJO,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AIFCRYsth/wywAAAEBJREFUGNNjZICCpwoM/xmQgPQDBkYGZEl0ANeALPlUgeE/Op8JZsozRYSJyGwmBgIArkDqPsKNyGyCjmQk5E0AOnZL+VlUuQAAAAAASUVORK5CYII=");
    }

    private Nautical() {
        // empty and private as it's a utils class
    }

    // IMAGENES BASE64
    // //////////////////////////////////////////////////////////////////////////////////////////
    public static String getBalizaImageBase64(final Integer tipoBaliza) {
        return iconosHtmlString.get(tipoBaliza);
    }

    public static Integer getTipoPunto(final double v) {
        if (v <= 0) {
            return Nautical.TIPO_PUNTO_NEGRO;
        } else if (v > 0 && v < 2) {
            return Nautical.TIPO_PUNTO_ROJO;
        } else if (v >= 2 && v < 3) {
            return Nautical.TIPO_PUNTO_NARANJA;
        } else if (v >= 3 && v < 4) {
            return Nautical.TIPO_PUNTO_AMARILLO;
        } else {
            return Nautical.TIPO_PUNTO_VERDE;
        }
    }

    // FROM
    // //////////////////////////////////////////////////////////////////////////////////////////

    public static double fromMeters(final double meters) {
        return meters / NAUTICAL_MILE_IN_METERS;
    }

    public static double fromMiles(final double miles) {
        return (miles * MILE_IN_METERS) / NAUTICAL_MILE_IN_METERS;
    }

    public static double fromKilometers(final double meters) {
        return fromMeters(meters * 1000);
    }

    // TO
    // ////////////////////////////////////////////////////////////////////////////////////////////

    public static double toMeters(final double nm) {
        return nm * NAUTICAL_MILE_IN_METERS;
    }

    public static double toMiles(final double nm) {
        return (nm * NAUTICAL_MILE_IN_METERS) / MILE_IN_METERS;
    }

    public static double toKilometers(final double nm) {
        return toMeters(nm) / 1000;
    }

    // FORMATEADORES
    // /////////////////////////////////////////////////////////////////////////////////

    public static String formatCordinate(final double c) {
        final DecimalFormat formato = new DecimalFormat("0.000000");
        return StringUtils.leftPad(formato.format(c), 10, ' ');
    }

    public static String formatCordinate(final BigDecimal c) {
        if (c == null) {
            return "";
        }
        final DecimalFormat formato = new DecimalFormat("0.000000");
        return StringUtils.leftPad(formato.format(c), 10, ' ');
    }

    public static String formatCourse(final double r) {
        final DecimalFormat formato = new DecimalFormat("000");
        return StringUtils.leftPad(formato.format(r), 4, ' ');
    }

    public static String formatCourse(final BigDecimal r) {
        if (r == null) {
            return "";
        }
        final DecimalFormat formato = new DecimalFormat("000");
        return StringUtils.leftPad(formato.format(r), 4, ' ');
    }

    public static String formatDistance(final double d) {
        final DecimalFormat formato = new DecimalFormat("0.000");
        return StringUtils.leftPad(formato.format(d), 6, ' ');
    }

    public static String formatDistance(final BigDecimal d) {
        if (d == null) {
            return "";
        }
        final DecimalFormat formato = new DecimalFormat("0.0");
        return StringUtils.leftPad(formato.format(d), 6, ' ');
    }

    public static Integer getTypeByName(final String name) {
        return typesByName.get(name);
    }
}
