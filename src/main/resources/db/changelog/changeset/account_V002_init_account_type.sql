insert into public.account_type(code, title, description)
    select 'PAYMENT_ACCOUNT_FIZ', 'расчётный счёт физ. лица', 'счет физического лица для расчетов в национальной валюте' union all
    select 'CURRENCY_ACCOUNT_FIZ', 'валютный счёт физ. лица', 'счет физического лица для расчетов в валюте' union all
    select 'PAYMENT_ACCOUNT_JUR', 'расчётный счёт юр. лица', 'счет юридического лица для расчетов в национальной валюте' union all
    select 'CURRENCY_ACCOUNT_JUR', 'валютный счёт юр. лица', 'счет юридического лица для расчетов в валюте'
;